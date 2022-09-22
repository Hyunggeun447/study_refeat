package solo.studyRefeat.domain.board.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import solo.studyRefeat.domain.board.dto.CreateBoardRequest;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.repository.BoardRepository;
import solo.studyRefeat.domain.common.uploadS3.UploadService;
import solo.studyRefeat.domain.user.entity.User;

@Service
@Transactional(readOnly = true)
public class BoardService {

  private final BoardRepository boardRepository;
  private final UploadService uploadService;

  public BoardService(BoardRepository boardRepository, UploadService uploadService) {
    this.boardRepository = boardRepository;
    this.uploadService = uploadService;
  }

  public void createBoard(CreateBoardRequest request, List<MultipartFile> multipartFiles, User user) {
    List<String> imageUrls = multipartFiles.stream()
        .map(uploadService::uploadImg)
        .collect(Collectors.toList());

    Board board = Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .user(user)
        .boardType(request.getBoardType())
        .imageUrls(imageUrls)
        .build();


    boardRepository.save(board);
  }

}
