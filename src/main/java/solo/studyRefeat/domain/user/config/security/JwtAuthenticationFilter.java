package solo.studyRefeat.domain.user.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import solo.studyRefeat.domain.user.pojo.CustomUserDetails;
import solo.studyRefeat.domain.user.repository.LogoutTokenRedisRepository;
import solo.studyRefeat.domain.user.service.CustomUserDetailService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  private final JwtTokenProvider jwtTokenProvider;
  private final LogoutTokenRedisRepository logoutTokenRedisRepository;
  private final CustomUserDetailService customUserDetailService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    try {
      String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

      if(token != null){

        String userEmail = jwtTokenProvider.getUserEmail(token);
        if(userEmail != null) {
          CustomUserDetails userDetails = customUserDetailService.loadUserByUsername(userEmail);

          validateAccessToken(token, userDetails);
          Authentication authentication = jwtTokenProvider.getAuthentication(userDetails);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
    } catch (SignatureException | MalformedJwtException e) {
      logger.info("????????? ?????????????????????.");
    } catch (ExpiredJwtException e) {
      logger.info("????????? ?????????????????????.");
    }
    chain.doFilter(request,response);
  }

  //TODO: ???????????? ?????? ?????? ?????????
  private void checkLogout(String token) {
    logoutTokenRedisRepository.existsByLogoutToken(token)
        .orElseThrow(IllegalArgumentException::new);
  }

  //TODO: ?????? ?????? ?????? ?????? ?????? ?????????
  private void validateAccessToken(String token, CustomUserDetails userDetails) {
    if(!jwtTokenProvider.validateToken(token, userDetails)) throw new IllegalArgumentException();
  }

}