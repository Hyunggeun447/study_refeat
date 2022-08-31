package solo.studyRefeat.domain.chat.util;

import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.entity.ChatRoom;

public class ChatRoomConverter {

  public static ChatRoom toChatRoom(CreateChatRoomRequest request) {
    return ChatRoom.builder()
        .roomName(request.getRoomName())
        .build();
  }

}
