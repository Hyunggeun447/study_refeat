package solo.studyRefeat.domain.chat.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "chat")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE chat SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Length(min = 1, max = 2000)
  private String message;

  @ManyToOne(fetch = FetchType.LAZY)
  private User sender;

  @ManyToOne(fetch = FetchType.LAZY)
  private ChatRoom chatRoom;
}
