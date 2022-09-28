package solo.studyRefeat.domain.board.repository;

import java.util.Optional;
import org.springframework.data.domain.Slice;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.value.BoardType;
import solo.studyRefeat.domain.board.value.SearchType;

public interface BoardSearchRepository {

  Slice<Board> findByKeyword(Optional<String> keyword, BoardType boardType, SearchType searchType);

}
