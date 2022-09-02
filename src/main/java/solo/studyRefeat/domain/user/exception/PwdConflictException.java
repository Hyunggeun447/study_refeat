package solo.studyRefeat.domain.user.exception;


import solo.studyRefeat.domain.common.exception.ConflictException;

public class PwdConflictException extends ConflictException {

  private static final String PWD_CONFLICT = "이미 등록되어 있는 패스워드 입니다.";

  public PwdConflictException() {
    super(PWD_CONFLICT);
  }

  public PwdConflictException(String message) {
    super(message);
  }
}
