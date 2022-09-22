package solo.studyRefeat.domain.board.util;

import java.util.List;
import solo.studyRefeat.domain.board.dto.CreateBoardRequest;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.user.entity.User;

public class BoardConverter {

  public static Board toBoard(CreateBoardRequest request,
      List<String> imageUrls, User user
  ) {

    return Board.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .user(user)
        .boardType(request.getBoardType())
        .imageUrls(imageUrls)
        .build();
  }

}
