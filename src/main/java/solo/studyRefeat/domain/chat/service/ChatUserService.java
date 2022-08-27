package solo.studyRefeat.domain.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.chat.repository.ChatUserRepository;

@Service
@Transactional(readOnly = true)
public class ChatUserService {

  private final ChatUserRepository chatUserRepository;

  public ChatUserService(ChatUserRepository chatUserRepository) {
    this.chatUserRepository = chatUserRepository;
  }
}
