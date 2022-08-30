package solo.studyRefeat.domain.chat.repository;

import solo.studyRefeat.domain.chat.entity.ChatUser;

public interface ChatUserQueryDslRepository {

  ChatUser findChatUserByChatRoomAndUser(Long chatRoomId, Long userId);

}
