package solo.studyRefeat.domain.user.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

  private String password;

  public Password(String password) {
    Assert.notNull(password, "password should not be empty");
    this.password = password;
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

}
