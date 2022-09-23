package solo.studyRefeat.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.dto.AddChatUserRequest;
import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.dto.DeleteChatUserRequest;
import solo.studyRefeat.domain.chat.service.ChatRoomService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.pojo.CustomUserDetails;
import solo.studyRefeat.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;
  private final UserService userService;

  @PostMapping
  public ResponseEntity<Long> createChatRoom(
      @RequestBody CreateChatRoomRequest request,
      @CurrentUser CustomUserDetails user) {

    User checkedUser = userService.checkUser(user);
    Long chatRoomId = chatRoomService.createChatRoom(request, checkedUser);
    return ResponseEntity.ok(chatRoomId);
  }

  @PutMapping("/addUser")
  public ResponseEntity<Long> addChatUser(
      @RequestBody AddChatUserRequest request,
      @CurrentUser CustomUserDetails user) {

    userService.checkUser(user);
    Long chatRoomId = chatRoomService.addChatUser(request);
    return ResponseEntity.ok(chatRoomId);
  }

  @DeleteMapping
  public ResponseEntity deleteUser(
      @RequestBody DeleteChatUserRequest request,
      @CurrentUser CustomUserDetails user) {
    User checkedUser = userService.checkUser(user);
    chatRoomService.deleteChatUser(request, checkedUser);
    return ResponseEntity.noContent().build();
  }
}
