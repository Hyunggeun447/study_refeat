package solo.studyRefeat.domain.chat.repository;

import static solo.studyRefeat.domain.chat.entity.QChatMessage.chatMessage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import solo.studyRefeat.domain.chat.entity.ChatMessage;

public class ChatMessageQueryDslRepositoryImpl implements ChatMessageQueryDslRepository{

  private final JPAQueryFactory jpaQueryFactory;

  public ChatMessageQueryDslRepositoryImpl(EntityManager em) {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }

  @Override
  public List<ChatMessage> findByChatRoomId(Long chatRoomId) {
    return jpaQueryFactory.selectFrom(chatMessage)
        .where(chatMessage.chatRoom.id.eq(chatRoomId))
        .fetch();
  }
}
