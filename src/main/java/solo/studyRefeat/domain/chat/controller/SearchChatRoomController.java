package solo.studyRefeat.domain.chat.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.service.ChatRoomService;
import solo.studyRefeat.domain.user.aop.annotation.CurrentUser;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class SearchChatRoomController {

  private final ChatRoomService chatRoomService;
  private final UserService userService;

  @GetMapping
  public List<ChatRoom> getChatRoom(
      @RequestParam("roomName") Optional<String> roomName,
      @CurrentUser Optional<User> user) {

    if (roomName.isPresent()) {
      return chatRoomService.findChatRoomByRoomName(roomName.get());
    } else if (user.isPresent()) {

      userService.checkUser(user.get());

      return chatRoomService.findMyChatRoom(user.get().getId());
    } else {
      return chatRoomService.findAll();
    }
  }
}
