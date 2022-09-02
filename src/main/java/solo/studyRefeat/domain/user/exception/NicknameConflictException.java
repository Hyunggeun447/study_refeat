package solo.studyRefeat.domain.user.exception;

import solo.studyRefeat.domain.common.exception.ConflictException;

public class NicknameConflictException extends ConflictException {

  private static final String NICKNAME_CONFLICT = "이미 등록되어 있는 닉네임 입니다.";

  public NicknameConflictException() {
    super(NICKNAME_CONFLICT);
  }

  public NicknameConflictException(String message) {
    super(message);
  }
}
