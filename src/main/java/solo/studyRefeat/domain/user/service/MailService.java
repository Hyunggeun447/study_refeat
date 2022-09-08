package solo.studyRefeat.domain.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;

  public boolean send(String subject, String text, String from, String to) {
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setSubject(subject);
      helper.setText(text);
      helper.setFrom(from);
      helper.setTo(to);
      javaMailSender.send(message);
      return true;
    } catch (MessagingException e) {
    }

    return false;
  }

}
