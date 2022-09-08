package solo.studyRefeat.domain.user.entity;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo {

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "profile_url")
  private String profileUrl;

  public void changeNickname(String newNickname) {
    this.nickname = newNickname;
  }

  public void changeProfileUrl(String newProfileUrl) {
    this.profileUrl = newProfileUrl;
  }

  public Optional<String> getProfileUrl() {
    if(this.profileUrl == null) {
      return Optional.empty();
    } else {
      return Optional.of(this.profileUrl);
    }
  }
}
