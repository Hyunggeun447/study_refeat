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
  public Long createChatRoom(CreateChatRoomRequest request) {
    ChatRoom chatRoom = chatRoomRepository.save(toChatRoom(request));

    request.getUserIds().forEach(userId ->
        {
          User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
          chatUserRepository.save(new ChatUser(chatRoom, user));
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

    chatUserRepository.save(new ChatUser(chatRoom, user));
    return chatRoom.getId();
  }

  @Transactional
  public void deleteChatUser(DeleteChatUserRequest request) {
    ChatUser chatUser = chatUserRepository.findChatUserByChatRoomAndUser(
        request.getChatRoomId(), request.getUserId());

    chatUser.deleteChatUser();
  }

  public List<ChatRoom> findMyChatRoom(Long userId) {
    return chatRoomRepository.findChatRoomByUserId(userId);
  }

  public List<ChatRoom> findChatRoomByRoomName(String roomName) {
    return chatRoomRepository.findChatRoomByRoomName(roomName);
  }

  public ChatRoom findChatRoomById(Long chatRoomId) {
    return chatRoomRepository.findById(chatRoomId)
        .orElseThrow(RuntimeException::new);
  }
}
