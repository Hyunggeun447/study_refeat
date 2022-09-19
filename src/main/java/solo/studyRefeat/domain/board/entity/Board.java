package solo.studyRefeat.domain.board.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "board")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE board SET is_deleted = true WHERE id = ?")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated
  @Column(name = "boardType")
  private BoardType boardType;

  @Builder.Default
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> imageUrls = new ArrayList<>();

  @Builder.Default
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  public void addUser(User user) {
    Assert.notNull(user, "chatRoom must be provided");

    if (!Objects.isNull(this.user)) {
      this.user.getChatMessages().remove(this);
    }

    user.addBoard(this);
    this.user = user;
  }

  public void deleteUser() {
    this.user.deleteBoard(this);
    this.user = null;
  }
}
