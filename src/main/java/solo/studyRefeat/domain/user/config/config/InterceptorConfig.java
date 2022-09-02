package solo.studyRefeat.domain.user.config.config;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import solo.studyRefeat.domain.common.log.LogInterceptor;
import solo.studyRefeat.domain.user.aop.CurrentUserArgumentResolver;
import solo.studyRefeat.domain.user.config.security.RedisInterceptor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

  private final LogInterceptor logInterceptor;
  private final RedisInterceptor permissionInterceptor;
  private final CurrentUserArgumentResolver currentUserArgumentResolver;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(permissionInterceptor)
        .addPathPatterns("/api/v1/users/login");
    registry.addInterceptor(logInterceptor)
        .addPathPatterns("/**");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(currentUserArgumentResolver);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }
}