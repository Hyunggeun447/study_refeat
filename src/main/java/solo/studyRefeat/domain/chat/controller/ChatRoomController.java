package solo.studyRefeat.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.service.ChatRoomService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  @PostMapping
  public Long createChatRoom(CreateChatRoomRequest request,
      @CurrentUser User user) {

    Long chatRoomId = chatRoomService.createChatRoom(request, user);

    return chatRoomId;
  }

}
