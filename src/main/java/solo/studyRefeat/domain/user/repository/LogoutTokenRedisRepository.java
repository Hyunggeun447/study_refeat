package solo.studyRefeat.domain.user.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import solo.studyRefeat.domain.user.pojo.LogoutToken;

public interface LogoutTokenRedisRepository extends CrudRepository<LogoutToken, String> {

  Optional<LogoutToken> existsByLogoutToken(String token);

}
