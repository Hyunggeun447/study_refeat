package solo.studyRefeat.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Slice;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.value.BoardType;
import solo.studyRefeat.domain.board.value.SearchType;

public class BoardSearchRepositoryImpl implements BoardSearchRepository{

  private final JPAQueryFactory jpaQueryFactory;

  public BoardSearchRepositoryImpl(EntityManager em) {
    this.jpaQueryFactory = new JPAQueryFactory(em);
  }


  @Override
  public Slice<Board> findByKeyword(Optional<String> keyword, BoardType boardType,
      SearchType searchType) {

//    jpaQueryFactory.selectFrom(board)
//        .where(searchType.toBooleanBuilder(keyword))
    return null;
  }
}
