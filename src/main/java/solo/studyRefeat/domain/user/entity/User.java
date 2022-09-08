package solo.studyRefeat.domain.user.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import solo.studyRefeat.domain.chat.entity.ChatMessage;
import solo.studyRefeat.domain.chat.entity.ChatUser;
import solo.studyRefeat.domain.common.entity.BaseTime;

@Entity
@Table(name = "users")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Embedded
  @Column(name = "password")
  private Password password;

  @Embedded
  private InfoNonChanged infoNonChanged;

  @Embedded
  private UserInfo userInfo;

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChatUser> chatUsers = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "sender")
  private List<ChatMessage> chatMessages = new ArrayList<>();

  @Builder.Default
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  @Builder.Default
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Authority> authorities = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public void addChatUSer(ChatUser chatUser) {
    this.chatUsers.add(chatUser);
  }

  public void deleteChatUser(ChatUser chatUser) {
    this.chatUsers.remove(chatUser);
  }

  public void addChatMessage(ChatMessage chatMessage) {
    this.chatMessages.add(chatMessage);
  }

  public List<String> getAuthorities() {
    return authorities.stream()
        .map(Authority::getRole)
        .collect(Collectors.toList());
  }

  public void addAuthority(Authority authority) {
    authorities.add(authority);
  }
}
