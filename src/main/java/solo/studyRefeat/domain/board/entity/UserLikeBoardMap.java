package solo.studyRefeat.domain.board.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.http.util.Asserts;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;
import solo.studyRefeat.domain.user.entity.User;

@Entity
@Table(name = "user_like_board_map")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE user_like_board_map SET is_deleted = true WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLikeBoardMap {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public UserLikeBoardMap(Board board, User user) {
    addBoard(board);
    addUser(user);
  }

  public void addBoard(Board board) {
    Assert.notNull(board, "board must be provided");

    if (!Objects.isNull(this.board)) {
      this.board.getUserLikeBoardMaps().remove(this);
    }

    board.addLikeUser(this);
    this.board = board;
  }

  public void addUser(User user) {
    Assert.notNull(user, "user must be provided");

    if (!Objects.isNull(this.user)) {
      this.user.getUserLikeBoardMaps().remove(this);
    }

    user.addLikeBoard(this);
    this.user = user;
  }
}
