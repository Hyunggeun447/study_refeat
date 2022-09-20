package solo.studyRefeat.domain.board.value;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BoardType {

  FREE("자유게시판"),

  HUMOR("유머게시판")
  ;

  private final String category;

}
