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
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import solo.studyRefeat.domain.common.entity.BaseTime;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "chat")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE chat SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Length(min = 1, max = 2000)
  private String message;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User sender;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chatRoom_id")
  private ChatRoom chatRoom;

  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  public void addSender(User sender) {
    Assert.notNull(sender, "user must be provided");

    if (!Objects.isNull(this.sender)) {
      this.sender.getChatMessages().remove(this);
    }

    sender.addChatMessage(this);
    this.sender = sender;
  }

  public void addChatRoom(ChatRoom chatRoom) {
    Assert.notNull(chatRoom, "chatRoom must be provided");

    if (!Objects.isNull(this.chatRoom)) {
      this.chatRoom.getChatMessages().remove(this);
    }

    chatRoom.addChatMessage(this);
    this.chatRoom = chatRoom;
  }
}
