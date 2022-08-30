package solo.studyRefeat.domain.chat.repository;

import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.entity.ChatUser;
import solo.studyRefeat.domain.user.entity.User;

public interface ChatUserQueryDslRepository {

  ChatUser findChatUser(ChatRoom chatRoom, User user);

}
