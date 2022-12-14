package solo.studyRefeat.domain.user.pojo;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("logoutToken")
@Getter
@Builder
public class LogoutToken {

  @Id
  private String email;

  private String logoutToken;

  @TimeToLive
  private Long expiration;

  public static LogoutToken of(String email, String token,  Long remainingMilliSeconds) {
    return LogoutToken.builder()
        .email(email)
        .logoutToken(token)
        .expiration(remainingMilliSeconds / 1000)
        .build();
  }

}
