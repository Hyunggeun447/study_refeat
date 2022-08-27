package solo.studyRefeat.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.chat.repository.ChatRoomRepository;

@Service
@Transactional(readOnly = true)
public class ChatRoomService {

  private final ChatRoomRepository chatRoomRepository;

  public ChatRoomService(
      ChatRoomRepository chatRoomRepository) {
    this.chatRoomRepository = chatRoomRepository;
  }
}
