package solo.studyRefeat.domain.user.exception;


import solo.studyRefeat.domain.common.exception.BadRequestException;

public class WrongInfoException extends BadRequestException {

  private static final String WRONG_INFO = "잘못된 정보가 들어왔습니다.";

  public WrongInfoException() {
    super(WRONG_INFO);
  }

  public WrongInfoException(String message) {
    super(message);
  }
}
