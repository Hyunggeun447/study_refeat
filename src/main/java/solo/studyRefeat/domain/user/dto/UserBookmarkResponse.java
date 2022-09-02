package solo.studyRefeat.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UserBookmarkResponse {

  private Long total;

  private Long courseBookmark;

  private Long placeBookmark;

  public UserBookmarkResponse(Long total, Long courseBookmark, Long placeBookmark) {
    this.total = total;
    this.courseBookmark = courseBookmark;
    this.placeBookmark = placeBookmark;
  }
}
