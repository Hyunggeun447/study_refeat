package solo.studyRefeat.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solo.studyRefeat.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where u.infoNonChanged.email = :email")
  Optional<User> findByEmail(@Param("email") String email);
  @Query("select u from User u where u.userInfo.nickname = :nickname")
  Optional<User> findByNickname(@Param("nickname") String nickname);

}
