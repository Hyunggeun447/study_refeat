package solo.studyRefeat.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserCommentResponse {

  private Long total;

  private Long courseComment;

  private Long placeComment;

  public UserCommentResponse(Long total, Long courseComment, Long placeComment) {
    this.total = total;
    this.courseComment = courseComment;
    this.placeComment = placeComment;
  }
}
