package solo.studyRefeat.domain.user.config.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    javaMailSender.setHost("stmp.naver.com");
    javaMailSender.setUsername("아이디");
    javaMailSender.setPassword("비밀번호");

    javaMailSender.setPort(465);

    javaMailSender.setJavaMailProperties(getMailProperties());

    return javaMailSender;
  }

  private Properties getMailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.transport.protocal", "smtp");
    properties.setProperty("mail.stmp.auth", "true");
    properties.setProperty("mail.stmp.starttls,enable", "true");
    properties.setProperty("mail.debug", "true");
    properties.setProperty("mail.stmp.ssl.trust", "smtp.naver.com");
    properties.setProperty("mail.stmp.ssl.enable", "true");
    return properties;
  }
}
