package solo.studyRefeat.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserLikeResponse {
  private Long total;

  private Long courseLike;

  private Long placeLike;

  public UserLikeResponse(Long total, Long courseLike, Long placeLike) {
    this.total = total;
    this.courseLike = courseLike;
    this.placeLike = placeLike;
  }
}
