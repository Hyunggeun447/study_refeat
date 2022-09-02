package solo.studyRefeat.domain.user.exception;


import solo.studyRefeat.domain.common.exception.BadRequestException;

public class SignUpFailException extends BadRequestException {

  private static final String SIGN_UP_FAIL = "회원가입에 실패했습니다.";

  public SignUpFailException() {
    super(SIGN_UP_FAIL);
  }

  public SignUpFailException(String message) {
    super(message);
  }
}
