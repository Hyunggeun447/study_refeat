package solo.studyRefeat.domain.board.controller;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solo.studyRefeat.domain.board.dto.ChangeBoardRequest;
import solo.studyRefeat.domain.board.dto.CreateBoardRequest;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.service.BoardSearchService;
import solo.studyRefeat.domain.board.service.BoardService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.pojo.CustomUserDetails;
import solo.studyRefeat.domain.user.service.UserService;

@RestController
@RequestMapping("/v1/board")
public class BoardController {

  private final BoardService boardService;
  private final UserService userService;
  private final BoardSearchService boardSearchService;

  public BoardController(BoardService boardService,
      UserService userService,
      BoardSearchService boardSearchService) {
    this.boardService = boardService;
    this.userService = userService;
    this.boardSearchService = boardSearchService;
  }

  @PostMapping()
  public ResponseEntity<Long> createBoard(
      @RequestPart("request") CreateBoardRequest request,
      @RequestPart("images") List<MultipartFile> images,
      @CurrentUser CustomUserDetails userDetails) {
    User user = userService.checkUser(userDetails);
    Long boardId = boardService.createBoard(request, images, user);
    return ResponseEntity.ok(boardId);
  }

  @PutMapping("/{boardId}")
  public void chageBoard(
      @PathVariable("boardId") Long boardId,
      @RequestPart("request") ChangeBoardRequest request,
      @RequestPart("images") List<MultipartFile> images,
      @CurrentUser CustomUserDetails userDetails) {

    User user = userService.checkUser(userDetails);

    boardService.changeBoard(boardId, request, images, user);
  }

  @GetMapping("/{boardId}")
  public ResponseEntity<Board> getBoard(@PathVariable("boardId") Long boardId) {
    Board board = boardSearchService.findById(boardId);

    return ResponseEntity.ok(board);
  }

  @GetMapping()
  public ResponseEntity<Slice<Board>> getAll(
      @PageableDefault(page = 0, size = 15) Pageable pageable) {
    Slice<Board> boards = boardSearchService.findAll(pageable);
    return ResponseEntity.ok(boards);
  }
}
