package solo.studyRefeat.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.user.service.MailService;

@RestController
@RequiredArgsConstructor
public class MailController {

  private final MailService mailService;

  /**
   * 인증 코드 발송
   * @param email 코드받을 email
   * @return 인증 코드
   * 인증코드를 return받에 presentation 계층에서 코드의 정합성을 비교한다.
   */
  @PostMapping("/login/mailConfim")
  public String mailConfirm(@RequestParam("email") String email) throws Exception {
    String code = mailService.sendSimpleMessage(email);
    return code;
  }
}
