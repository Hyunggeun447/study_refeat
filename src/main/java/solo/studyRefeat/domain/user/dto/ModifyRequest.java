package solo.studyRefeat.domain.user.dto;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.studyRefeat.domain.user.entity.Gender;

@Getter
@NoArgsConstructor
public class ModifyRequest {

  private String nickname;

  private String birth;

  private Gender gender;

  public ModifyRequest(String nickname, String birth, Gender gender) {
    this.nickname = nickname;
    this.birth = birth;
    this.gender = gender;
  }

  public void setNickname(String nickname) {
    checkBlank(nickname);
    this.nickname = nickname;
  }

  public void setBirth(String birth) {
    checkBlank(birth);
    this.birth = birth;
  }

  public void setSex(Gender gender) {
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
