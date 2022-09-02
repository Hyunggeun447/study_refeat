package solo.studyRefeat.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import solo.studyRefeat.domain.user.config.security.JwtHeader;

@Builder
@Getter
public class TokenResponse {

  private String grantType;
  private String accessToken;
  private String refreshToken;

  public static TokenResponse of(String accessToken, String refreshToken) {
    return TokenResponse.builder()
        .grantType(JwtHeader.GRANT_TYPE.getValue())
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

}
