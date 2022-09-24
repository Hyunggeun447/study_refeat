package solo.studyRefeat.domain.board.service;

import static solo.studyRefeat.domain.board.util.BoardConverter.toBoard;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solo.studyRefeat.domain.board.dto.ChangeBoardRequest;
import solo.studyRefeat.domain.board.dto.CreateBoardRequest;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.repository.BoardRepository;
import solo.studyRefeat.domain.common.uploadS3.UploadService;
import solo.studyRefeat.domain.user.entity.User;

@Service
@Transactional
public class BoardService {

  private final BoardRepository boardRepository;
  private final UploadService uploadService;

  public BoardService(BoardRepository boardRepository, UploadService uploadService) {
    this.boardRepository = boardRepository;
    this.uploadService = uploadService;
  }

  public Long createBoard(CreateBoardRequest request, List<MultipartFile> multipartFiles, User user) {
    List<String> imageUrls = multipartFiles.stream()
        .map(uploadService::uploadImg)
        .collect(Collectors.toList());

    Board board = toBoard(request, imageUrls, user);
    Board createdBoard = boardRepository.save(board);

    return createdBoard.getId();
  }

  public Long changeBoard(Long boardId, ChangeBoardRequest request,
      List<MultipartFile> multipartFiles, User user) {

    Board board = boardRepository.findById(boardId)
        .orElseThrow(RuntimeException::new);

    List<String> imageUrls = multipartFiles.stream()
        .map(uploadService::uploadImg)
        .collect(Collectors.toList());

    board.changeTitle(request.getTitle(), user);
    board.changeContent(request.getContent(), user);
    board.changeBoardType(request.getBoardType(), user);
    board.changeImageUrl(imageUrls, user);
    return board.getId();
  }

  public String deleteBoard(Long boardId, User user) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(RuntimeException::new);

    boolean hasAuth = user.getAuthorities().contains("ROLE_ADMIN");
    if (hasAuth) {
      boardRepository.delete(board);
      return "delete" + boardId;
    }

    boolean isWriter = board.getUser().equals(user);
    if (isWriter) {
      boardRepository.delete(board);
      return "delete" + boardId;
    }

    throw new RuntimeException("권한 없음");
  }

  public Long likeBoard(Long boardId, User user) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(RuntimeException::new);

    board.like(user);
    return board.getLikeCount();
  }

  public Long disLikeBoard(Long boardId, User user) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(RuntimeException::new);

    board.dislike(user);
    return board.getLikeCount();
  }
}
