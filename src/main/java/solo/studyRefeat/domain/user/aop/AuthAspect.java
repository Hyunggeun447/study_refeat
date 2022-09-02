package solo.studyRefeat.domain.user.aop;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import solo.studyRefeat.domain.user.config.security.JwtTokenProvider;
import solo.studyRefeat.domain.user.repository.UserRepository;

@Aspect
@Component
public class AuthAspect {

  private static final String HEADER = "Authorization";

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final HttpServletRequest httpServletRequest;

  public AuthAspect(JwtTokenProvider jwtTokenProvider,
      UserRepository userRepository, HttpServletRequest httpServletRequest) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.userRepository = userRepository;
    this.httpServletRequest = httpServletRequest;
  }

  @Before("@annotation(solo.studyRefeat.domain.user.aop.annotation.Auth)")
  public void checkToken() {
    String token = httpServletRequest.getHeader(HEADER);

    Long userId = jwtTokenProvider.getUserId(token);
    userRepository.findById(userId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
  }

}
