package solo.studyRefeat.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.studyRefeat.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
