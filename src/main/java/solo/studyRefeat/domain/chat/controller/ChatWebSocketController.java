package solo.studyRefeat.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
  public ChatMessage send(
      @DestinationVariable Long roomId,
      CreateMessageRequest request,
      @CurrentUser CustomUserDetails user) {

    User checkedUser = userService.checkUser(user);

    return chatMessageService.createMessage(roomId, request, checkedUser);
  }

  @MessageExceptionHandler(RuntimeException.class)
  public void handleException(RuntimeException e) {
    log.info(e.getMessage());
  }
}
