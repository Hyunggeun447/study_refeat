package solo.studyRefeat.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.studyRefeat.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
