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
import solo.studyRefeat.domain.board.value.BoardType;
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

  @ElementCollection(fetch = FetchType.LAZY)
  @Builder.Default
  private List<Long> likeUserId = new ArrayList<>();

  @Builder.Default
  private Long likeCount = 0L;

  @Builder.Default
  @Column(name = "is_deleted")
  private Boolean isDeleted = Boolean.FALSE;

  public void changeBoardType(BoardType newBoardType, User user) {
    Assert.notNull(newBoardType, "newBoardType must be provided");
    Assert.notNull(user, "user must be provided");

    boolean hasAuth = user.getAuthorities().contains("ROLE_ADMIN");
    boolean isWriter = this.user.equals(user);

    if (hasAuth || isWriter) {
      this.boardType = newBoardType;
    }
  }

  public void changeTitle(String newTitle, User user) {
    Assert.notNull(newTitle, "title must be provided");
    Assert.notNull(user, "user must be provided");

    validateWriter(user);
    this.title = newTitle;
  }

  public void changeContent(String newContent, User user) {
    Assert.notNull(newContent, "title must be provided");
    Assert.notNull(user, "user must be provided");

    validateWriter(user);
    this.content = newContent;
  }

  public void changeImageUrl(List<String> newImageUrls, User user) {
    Assert.notNull(user, "user must be provided");

    validateWriter(user);
    this.imageUrls = newImageUrls;
  }

  public void addUser(User user) {
    Assert.notNull(user, "user must be provided");

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

  public void like(User user) {
    expressLike(user);
    this.likeCount += 1;
  }

  public void disLike(User user) {
    expressLike(user);
    this.likeCount -= 1;

  }

  private void expressLike(User user) {
    Assert.notNull(user, "user must be provided");

    if (this.likeUserId.contains(user.getId())) {
      throw new RuntimeException("이미 좋아요를 눌렀습니다.");
    }

    this.likeUserId.add(user.getId());
  }

  private void validateWriter(User user) {
    if (this.user.equals(user)) {
      throw new RuntimeException("권한 없음");
    }
  }
}
