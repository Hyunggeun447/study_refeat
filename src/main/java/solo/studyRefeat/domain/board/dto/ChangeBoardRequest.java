package solo.studyRefeat.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import solo.studyRefeat.domain.board.value.BoardType;

@Builder
@Getter
public class ChangeBoardRequest {

  private String title;
  private String content;
  private BoardType boardType;
}
