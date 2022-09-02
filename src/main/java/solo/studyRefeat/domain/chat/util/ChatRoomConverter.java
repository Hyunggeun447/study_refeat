package solo.studyRefeat.domain.chat.util;

import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.dto.CreateMessageRequest;
import solo.studyRefeat.domain.chat.entity.ChatMessage;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.user.entity.User;

public class ChatRoomConverter {

  public static ChatRoom toChatRoom(CreateChatRoomRequest request) {
    return ChatRoom.builder()
        .roomName(request.getRoomName())
        .build();
  }

  public static ChatMessage toChatMessage(CreateMessageRequest request,
      ChatRoom chatRoom,
      User user) {

    return ChatMessage.builder()
        .chatRoom(chatRoom)
        .message(request.getMessage())
        .sender(user)
        .build();
  }

}
