package solo.studyRefeat.domain.user.repository;

import org.springframework.data.repository.CrudRepository;
import solo.studyRefeat.domain.user.pojo.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {

}
