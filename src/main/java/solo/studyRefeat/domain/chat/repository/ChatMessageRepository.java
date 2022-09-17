package solo.studyRefeat.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.studyRefeat.domain.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>,
    ChatMessageQueryDslRepository {


}
