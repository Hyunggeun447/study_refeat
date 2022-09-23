package solo.studyRefeat.domain.chat.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.dto.CreateMessageRequest;
import solo.studyRefeat.domain.chat.entity.ChatMessage;
import solo.studyRefeat.domain.chat.service.ChatMessageService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.pojo.CustomUserDetails;
import solo.studyRefeat.domain.user.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatWebSocketController {

  private final ChatMessageService chatMessageService;
  private final UserService userService;

  @MessageMapping("/room/{roomId}")
  @SendTo("/chat/room/{roomId}")
  public ResponseEntity<ChatMessage> send(
      @DestinationVariable Long roomId,
      CreateMessageRequest request,
      @CurrentUser CustomUserDetails user) {

    User checkedUser = userService.checkUser(user);

    ChatMessage message = chatMessageService.createMessage(roomId, request, checkedUser);
    return ResponseEntity.ok(message);
  }

  @MessageExceptionHandler(RuntimeException.class)
  public void handleException(RuntimeException e) {
    log.info(e.getMessage());
  }

  @GetMapping("/{chatRoomId}")
  public ResponseEntity<List<ChatMessage>>  getChatMessageByChatRoomId(@PathVariable(name = "chatRoomId") Long chatRoomId) {
    List<ChatMessage> chatMessages = chatMessageService.findChatMessageByChatRoomId(
        chatRoomId);
    return ResponseEntity.ok(chatMessages);
  }
}
