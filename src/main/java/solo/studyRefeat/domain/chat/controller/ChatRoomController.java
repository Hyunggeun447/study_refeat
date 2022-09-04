package solo.studyRefeat.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.dto.AddChatUserRequest;
import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.service.ChatRoomService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;
  private final UserService userService;

  @PostMapping
  public Long createChatRoom(CreateChatRoomRequest request,
      @CurrentUser User user) {

    userService.checkUser(user);
    Long chatRoomId = chatRoomService.createChatRoom(request);

    return chatRoomId;
  }

  @PutMapping("/addUser")
  public Long addChatUser(AddChatUserRequest request,
      @CurrentUser User user) {

    userService.checkUser(user);
    Long chatRoomId = chatRoomService.addChatUser(request);
    return chatRoomId;
  }



}
