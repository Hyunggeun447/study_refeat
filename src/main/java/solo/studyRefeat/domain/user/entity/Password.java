package solo.studyRefeat.domain.user.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

  private String password;

  public Password(String password) {
    this.password = password;
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

}
