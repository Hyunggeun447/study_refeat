package solo.studyRefeat.domain.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.board.entity.Board;
import solo.studyRefeat.domain.board.repository.BoardRepository;

@Service
@Transactional(readOnly = true)
public class BoardSearchService {

  private final BoardRepository boardRepository;

  public BoardSearchService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public Board findById(Long boardId) {
    Board board = boardRepository.findById(boardId).orElseThrow(RuntimeException::new);
    return board;
  }
}
