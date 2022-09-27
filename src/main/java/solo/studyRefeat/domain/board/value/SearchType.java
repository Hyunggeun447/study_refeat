package solo.studyRefeat.domain.board.value;

import static solo.studyRefeat.domain.board.entity.QBoard.*;

import com.querydsl.core.BooleanBuilder;
import java.util.Optional;

public enum SearchType {

  제목 {
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      if (keyword.isEmpty()) {
        return null;
      }
      BooleanBuilder builder = new BooleanBuilder();
      String[] splitedKeyword = keyword.get().split(" ");
      for (String value : splitedKeyword) {
        builder.and(board.title.containsIgnoreCase(value));
      }
      return builder;
    }
  },
  내용 {
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      if (keyword.isEmpty()) {
        return null;
      }
      BooleanBuilder builder = new BooleanBuilder();
      String[] splitedKeyword = keyword.get().split(" ");
      for (String value : splitedKeyword) {
        builder.and(board.content.containsIgnoreCase(value));
      }
      return builder;
    }
  },
  제목_내용 {
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      if (keyword.isEmpty()) {
        return null;
      }
      BooleanBuilder builder = new BooleanBuilder();
      String[] splitedKeyword = keyword.get().split(" ");
      for (String value : splitedKeyword) {
        builder.and(board.title.containsIgnoreCase(value));
        builder.and(board.content.containsIgnoreCase(value));
      }
      return builder;
    }
  },
  작성자 {
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      if (keyword.isEmpty()) {
        return null;
      }
      BooleanBuilder builder = new BooleanBuilder();
      String[] splitedKeyword = keyword.get().split(" ");
      for (String value : splitedKeyword) {
        builder.and(board.user.userInfo.nickname.containsIgnoreCase(value));
      }
      return builder;
    }
  },
  
  제목_내용_댓글내용 {
    // TODO: 2022/09/24  
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      return null;
    }
  },
  댓글내용 {
    // TODO: 2022/09/24  
    @Override
    public BooleanBuilder toBooleanBuilder(Optional<String> keyword) {
      return null;
    }
  };

  abstract public BooleanBuilder toBooleanBuilder(Optional<String> keyword);


}
