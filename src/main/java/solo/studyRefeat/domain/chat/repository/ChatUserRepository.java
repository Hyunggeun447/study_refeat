package solo.studyRefeat.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.studyRefeat.domain.chat.entity.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long>, ChatUserQueryDslRepository {

}
