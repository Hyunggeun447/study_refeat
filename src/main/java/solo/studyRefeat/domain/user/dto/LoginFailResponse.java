package solo.studyRefeat.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginFailResponse {

  private Integer count;

  private String expiredAt;

  public LoginFailResponse(Integer count, String expiredAt) {
    this.count = count;
    this.expiredAt = expiredAt;
  }
}
