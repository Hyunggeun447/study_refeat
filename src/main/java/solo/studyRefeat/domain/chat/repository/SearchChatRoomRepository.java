package solo.studyRefeat.domain.chat.repository;

import java.util.List;
import java.util.Optional;
import solo.studyRefeat.domain.chat.entity.ChatRoom;

public interface SearchChatRoomRepository {

  List<ChatRoom> findChatRoomByUserId(Long userId);

  List<ChatRoom> findChatRoomByRoomName(Optional<String> roomName);

}
