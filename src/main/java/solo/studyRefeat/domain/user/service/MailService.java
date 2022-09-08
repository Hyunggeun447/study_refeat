package solo.studyRefeat.domain.user.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;

  private final String ePw = createKey();

  public String sendSimpleMessage(String to) throws Exception {

    MimeMessage message = createMessage(to);

    try {
      javaMailSender.send(message);
    } catch (MailException es) {
      es.printStackTrace();
      throw new IllegalArgumentException();
    }

    return ePw;
  }

  private MimeMessage createMessage(String to)
      throws MessagingException, UnsupportedEncodingException {

    MimeMessage message = javaMailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, to);
    message.setSubject("메일 제목");  //메일 제목

    String msgg = "";
    msgg += "<div style='margin:100px;'>";
    msgg += "<h1> 안녕하세요 </h1>";
    msgg += "<br>";
    msgg += "<h1> 아래 코드를 회원가입 창으로 돌아가 입력해주세요 </h1>";
    msgg += "<br>";
    msgg += "Code : <strong>";
    msgg += ePw + "</strong><div><br/>";
    msgg += "</div>";

    // 내용, charset 타입, subtype
    message.setText(msgg, "utf-8", "html");

    // 보내는 사람의 이메일 주소, 이름
    message.setFrom(new InternetAddress("보내는 사람의 이메일 주소", "Admin"));

    return message;
  }

  /**
   * 랜덤 인증 코드 생성
   * @return
   */
  private String createKey() {
    StringBuffer key = new StringBuffer();
    Random rnd = new Random();

    for (int i = 0; i < 6; i++) {             // 인증 코드 8자리
      int index = rnd.nextInt(3);      // 0~2 까지 랜덤으로 하위 동작이 발생

      switch (index) {
        case 0:
          key.append((char) ((int) (rnd.nextInt(26)) + 97)); // 영어 소문자
          break;
        case 1:
          key.append((char) ((int) (rnd.nextInt(26)) + 65)); // 영어 대문자
          break;
        case 2:
          key.append(rnd.nextInt(10)); // 숫자 0~9
          break;
      }
    }
    return key.toString();
  }
}
