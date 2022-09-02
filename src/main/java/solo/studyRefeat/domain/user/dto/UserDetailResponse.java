package solo.studyRefeat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import solo.studyRefeat.domain.user.entity.Gender;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {

  private Long id;

  private String email;

  private String nickname;

  private String profileImage;

  private String birth;

  private Gender gender;

  private String createdAt;

  private String updatedAt;

  private UserCounts counts;
}
