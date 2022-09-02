package solo.studyRefeat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCounts {

  private Long course;

  private Long comments;

  private UserBookmarkResponse bookmarks;

}
