package solo.studyRefeat.domain.board.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import solo.studyRefeat.domain.board.dto.CreateBoardRequest;
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

  public BoardController(BoardService boardService,
      UserService userService) {
    this.boardService = boardService;
    this.userService = userService;
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
}
