package solo.studyRefeat.domain.chat.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.dto.DeleteChatUserRequest;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.entity.ChatUser;
import solo.studyRefeat.domain.chat.repository.ChatRoomRepository;
import solo.studyRefeat.domain.user.entity.Password;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.repository.UserRepository;

@SpringBootTest
@Transactional
class ChatRoomServiceTest {

  @Autowired
  ChatRoomService chatRoomService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ChatRoomRepository chatRoomRepository;

  User user;
  @BeforeEach
  void setup() {
    user = userRepository.save(User.builder()
        .nickname("nick")
        .password(new Password("pass"))
        .build());
    userId = user.getId();
    List<Long> userIds = new ArrayList<>();
    userIds.add(userId);
    CreateChatRoomRequest request = CreateChatRoomRequest.builder()
        .roomName("roomName")
        .userIds(userIds)
        .build();
    chatRoomId = chatRoomService.createChatRoom(request, user);

    ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(RuntimeException::new);

    ChatUser chatUser = chatRoom.getChatUsers().get(0);
    String nickname = chatUser.getUser().getNickname();
    System.out.println("nickname = " + nickname);
  }

  Long userId;
  Long chatRoomId;

  @Test
  @DisplayName("")
  public void Test() throws Exception {

//    System.out.println(chatRoomId);
//    System.out.println(userId);
    DeleteChatUserRequest request = DeleteChatUserRequest.builder()
        .chatRoomId(chatRoomId)
        .userId(userId)
        .build();
    chatRoomService.deleteChatUser(request, user);

    ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(RuntimeException::new);

    Assertions.assertThat(chatRoom.getChatUsers().size()).isEqualTo(0);
//    String nickname = chatUser.getUser().getNickname();
//    System.out.println("nickname = " + nickname);
  }


}