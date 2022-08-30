package solo.studyRefeat.domain.chat.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;
import solo.studyRefeat.domain.common.entity.BaseTime;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "chat_user")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE chat_user SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatUser extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chatRoom_id")
  private ChatRoom chatRoom;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  public ChatUser(ChatRoom chatRoom, User user) {
    addChatRoom(chatRoom);
    addUser(user);
  }

  public void addChatRoom(ChatRoom chatRoom) {
    Assert.notNull(chatRoom,"chatRoom must be provided");

    if (!Objects.isNull(this.chatRoom)) {
      this.chatRoom.getChatUsers().remove(this);
    }

    chatRoom.addChatUser(this);
    this.chatRoom = chatRoom;
  }

  public void deleteChatRoom(ChatRoom chatRoom) {
    chatRoom.deleteChatUser(this);
    this.chatRoom = null;
  }

  public void addUser(User user) {
    Assert.notNull(user, "user must be provided");

    if (!Objects.isNull(this.user)) {
      this.user.getChatUsers().remove(this);
    }

    user.addChatUSer(this);
    this.user = user;
  }

  public void deleteUser(User user) {
    user.deleteChatUser(this);
    this.user = null;
  }
}
