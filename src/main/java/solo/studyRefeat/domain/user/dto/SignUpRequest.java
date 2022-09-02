package solo.studyRefeat.domain.user.dto;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.studyRefeat.domain.user.entity.Gender;

@Getter
@NoArgsConstructor
public class SignUpRequest {

  private String email;

  private String password;

  private String passwordCheck;

  private String nickname;

  private String birth;

  private Gender gender;

  public SignUpRequest(String email, String password, String passwordCheck, String nickname,
      String birth, Gender gender) {
    setEmail(email);
    setPassword(password);
    setPasswordCheck(passwordCheck);
    setNickname(nickname);
    setBirth(birth);
    setGender(gender);
  }

  public void setEmail(String email) {
    checkBlank(email);
    this.email = email;
  }

  public void setPassword(String password) {
    checkBlank(password);
    this.password = password;
  }

  public void setPasswordCheck(String passwordCheck) {
    checkBlank(passwordCheck);
    this.passwordCheck = passwordCheck;
  }

  public void setNickname(String nickname) {
    checkBlank(nickname);
    this.nickname = nickname;
  }

  public void setBirth(String birth) {
    checkBlank(birth);
    this.birth = birth;
  }

  public void setGender(Gender gender) {
    if(isNull(gender)) throw new RuntimeException();
    this.gender = gender;
  }

  private void checkBlank(String target) {
    //TODO: InvalidParamException
    if(isBlank(target)) {
      throw new RuntimeException();
    }
  }

}
