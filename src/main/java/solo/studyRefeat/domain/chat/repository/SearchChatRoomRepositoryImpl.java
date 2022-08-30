package solo.studyRefeat.domain.chat.repository;

import static solo.studyRefeat.domain.chat.entity.QChatRoom.*;
import static solo.studyRefeat.domain.user.entity.QUser.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import solo.studyRefeat.domain.chat.entity.ChatRoom;

public class SearchChatRoomRepositoryImpl implements SearchChatRoomRepository{

  private final JPAQueryFactory jpaQueryFactory;

  public SearchChatRoomRepositoryImpl(EntityManager em) {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }

  @Override
  public List<ChatRoom> findChatRoomByUserId(Long userId) {
    return jpaQueryFactory.selectFrom(chatRoom)
        .leftJoin(user, chatRoom.chatUsers.any().user)
        .where(user.id.eq(userId))
        .fetch();
  }
}
