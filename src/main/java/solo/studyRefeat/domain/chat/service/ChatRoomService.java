package solo.studyRefeat.domain.chat.service;

import static solo.studyRefeat.domain.chat.util.ChatRoomConverter.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.chat.dto.AddChatUserRequest;
import solo.studyRefeat.domain.chat.dto.CreateChatRoomRequest;
import solo.studyRefeat.domain.chat.dto.DeleteChatUserRequest;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.entity.ChatUser;
import solo.studyRefeat.domain.chat.repository.ChatRoomRepository;
import solo.studyRefeat.domain.chat.repository.ChatUserRepository;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;
  private final ChatUserRepository chatUserRepository;
  private final UserRepository userRepository;

  @Transactional
  public Long createChatRoom(CreateChatRoomRequest request, User user) {
    ChatRoom chatRoom = chatRoomRepository.save(toChatRoom(request, user));

    request.getUserIds().forEach(userId ->
        {
          User chatUser = userRepository.findById(userId).orElseThrow(RuntimeException::new);
          chatUserRepository.save(new ChatUser(chatRoom, chatUser));
        }
    );
    return chatRoom.getId();
  }

  @Transactional
  public Long addChatUser(AddChatUserRequest request) {
    ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
        .orElseThrow(RuntimeException::new);

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(RuntimeException::new);

    if (chatRoom.getChatUsers().contains(user)) {
      throw new RuntimeException("이미 존재하는 유저");
    }

    chatUserRepository.save(new ChatUser(chatRoom, user));
    return chatRoom.getId();
  }

  @Transactional
  public void deleteChatUser(DeleteChatUserRequest request, User user) {
    ChatUser chatUser = chatUserRepository.findChatUserByChatRoomAndUser(
        request.getChatRoomId(), request.getUserId());

    ChatRoom chatRoom = chatUser.getChatRoom();
    chatRoom.validateHost(user);

    chatUser.deleteChatUser();
  }

  public List<ChatRoom> findMyChatRoom(Long userId) {
    return chatRoomRepository.findChatRoomByUserId(userId);
  }

  public List<ChatRoom> findChatRoomByRoomName(String roomName) {
    return chatRoomRepository.findChatRoomByRoomName(roomName);
  }

  public List<ChatRoom> findAll() {
    return chatRoomRepository.findAll();
  }

  public ChatRoom findChatRoomById(Long chatRoomId) {
    return chatRoomRepository.findById(chatRoomId)
        .orElseThrow(RuntimeException::new);
  }

}
