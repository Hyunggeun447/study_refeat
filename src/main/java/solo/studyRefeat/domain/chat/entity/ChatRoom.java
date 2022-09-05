package solo.studyRefeat.domain.chat.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import solo.studyRefeat.domain.common.entity.BaseTime;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "chat_room")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE chat_room SET is_deleted = true WHERE id = ?")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoom extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "roomName")
  private String roomName;

  @Builder.Default
  @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChatMessage> chatMessages = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChatUser> chatUsers = new ArrayList<>();

  private Long hostId;

  @Builder.Default
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  public ChatRoom(String roomName) {
    this.roomName = roomName;
  }

  public void addChatUser(ChatUser chatUser) {
    this.chatUsers.add(chatUser);
  }

  public void deleteChatUser(ChatUser chatUser) {
    this.chatUsers.remove(chatUser);
  }

  public void addChatMessage(ChatMessage chatMessage) {
    this.chatMessages.add(chatMessage);
  }

  public void validateHost(User user) {
    if (!this.hostId.equals(user.getId())) {
      throw new RuntimeException("권한이 없습니다.");
    }
  }
}
