package solo.studyRefeat.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.chat.dto.CreateMessageRequest;
import solo.studyRefeat.domain.chat.entity.ChatMessage;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.repository.ChatMessageRepository;
import solo.studyRefeat.domain.chat.repository.ChatRoomRepository;
import solo.studyRefeat.domain.user.entity.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatMessageService {

  private final ChatMessageRepository chatMessageRepository;
  private final ChatRoomRepository chatRoomRepository;

  @Transactional
  public Long createMessage(CreateMessageRequest request, User user) {

    ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
        .orElseThrow(RuntimeException::new);

    ChatMessage chatMessage = ChatMessage.builder()
        .chatRoom(chatRoom)
        .message(request.getMessage())
        .sender(user)
        .build();

    return chatMessageRepository.save(chatMessage).getId();
  }

}
