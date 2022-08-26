package solo.studyRefeat.domain.chat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "chat_user")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE chat_user SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


}
