package solo.studyRefeat.domain.chat.repository;

import static solo.studyRefeat.domain.chat.entity.QChatUser.chatUser;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import solo.studyRefeat.domain.chat.entity.ChatRoom;
import solo.studyRefeat.domain.chat.entity.ChatUser;
import solo.studyRefeat.domain.chat.entity.QChatUser;
import solo.studyRefeat.domain.user.entity.User;

public class ChatUserQueryDslRepositoryImpl implements ChatUserQueryDslRepository{

  private final JPAQueryFactory jpaQueryFactory;

  public ChatUserQueryDslRepositoryImpl(EntityManager em) {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }

  @Override
  public ChatUser findChatUser(ChatRoom chatRoom, User user) {
    return jpaQueryFactory.selectFrom(QChatUser.chatUser)
        .where(
            QChatUser.chatUser.chatRoom.eq(chatRoom),
            QChatUser.chatUser.user.eq(user)
        )
        .fetchOne();
  }
}
