package solo.studyRefeat.domain.chat.repository;

import java.util.List;
import solo.studyRefeat.domain.chat.entity.ChatMessage;

public interface ChatMessageQueryDslRepository {

  List<ChatMessage> findByChatRoomId(Long chatRoomId);

}
