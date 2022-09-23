package solo.studyRefeat.domain.chat.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.service.ChatRoomService;
import solo.studyRefeat.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatRoom")
public class SearchChatRoomController {

  private final ChatRoomService chatRoomService;
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<ChatRoom>> getChatRoom(
      @RequestParam("roomName") Optional<String> roomName) {

    List<ChatRoom> chatRooms = chatRoomService.findChatRoomByRoomName(roomName);
    return ResponseEntity.ok(chatRooms);
  }
}
