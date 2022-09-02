package solo.studyRefeat.domain.user.config.security;

public enum JwtExpirationEnum {
  ACCESS_TOKEN_EXPIRATION_TIME("AccessToken 만료 시간 / 7일", 1000L * 60 * 60 * 24 * 7),
  LOGIN_EXPIRATION_TIME("로그인 횟수시도 만료 시간 / 6시간 ", 1000L * 60 * 60 * 6),
  BAN_EXPIRATION_TIME("로그인 금지 만료 시간 / 1일", 1000L * 60 * 60 * 24);

  private String description;
  private Long value;

  JwtExpirationEnum(String description, Long value) {
    this.description = description;
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public Long getValue() {
    return value;
  }
}

