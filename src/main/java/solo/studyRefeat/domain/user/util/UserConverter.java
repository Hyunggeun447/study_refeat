package solo.studyRefeat.domain.user.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import solo.studyRefeat.domain.user.dto.LoginResponse;
import solo.studyRefeat.domain.user.dto.OtherUserDetailResponse;
import solo.studyRefeat.domain.user.dto.SignUpRequest;
import solo.studyRefeat.domain.user.dto.UserBookmarkResponse;
import solo.studyRefeat.domain.user.dto.UserCommentResponse;
import solo.studyRefeat.domain.user.dto.UserCounts;
import solo.studyRefeat.domain.user.dto.UserDetailResponse;
import solo.studyRefeat.domain.user.dto.UserLikeResponse;
import solo.studyRefeat.domain.user.entity.Authority;
import solo.studyRefeat.domain.user.entity.InfoNonChanged;
import solo.studyRefeat.domain.user.entity.Password;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.entity.UserInfo;

public class UserConverter {

  public static User toUser(SignUpRequest signUpRequest) {
    User user = User.builder()
        .infoNonChanged(InfoNonChanged.builder()
            .email(signUpRequest.getEmail())
            .gender(signUpRequest.getGender())
            .birth(LocalDate.parse(signUpRequest.getBirth()))
            .build())
        .password(new Password(signUpRequest.getPassword()))
        .userInfo(UserInfo.builder()
            .nickname(signUpRequest.getNickname())
            .build())
        .build();
    Authority.ofUser(user);
    return user;
  }

  public static User toAdmin(SignUpRequest signUpRequest) {
    User user = User.builder()
        .password(new Password(signUpRequest.getPassword()))
        .infoNonChanged(InfoNonChanged.builder()
            .email(signUpRequest.getEmail())
            .gender(signUpRequest.getGender())
            .birth(LocalDate.parse(signUpRequest.getBirth()))
            .build())
        .userInfo(UserInfo.builder()
            .nickname(signUpRequest.getNickname())
            .build())
        .build();
    Authority.ofAdmin(user);
    Authority.ofUser(user);
    return user;
  }

  public static UserDetailResponse detailFromUser(
      User user,
      UserBookmarkResponse userBookmarkResponse,
      Long userComments,
      Long userCourse) {

    String profileUrl = user.getUserInfo().getProfileUrl()
        .orElse(null);

    UserDetailResponse userDetail = UserDetailResponse.builder()
        .id(user.getId())
        .email(user.getInfoNonChanged().getEmail())
        .nickname(user.getUserInfo().getNickname())
        .profileImage(profileUrl)
        .birth(fromLocalDate(user.getInfoNonChanged().getBirth()))
        .gender(user.getInfoNonChanged().getGender())
        .createdAt(fromLocalDateTime(user.getCreatedAt()))
        .updatedAt(fromLocalDateTime(user.getUpdatedAt()))
        .build();

    UserCounts counts = UserCounts.builder()
        .course(userCourse)
        .comments(userComments)
        .bookmarks(userBookmarkResponse)
        .build();

    userDetail.setCounts(counts);

    return userDetail;
  }


  public static OtherUserDetailResponse otherDetailFromUser(
      User user,
      UserBookmarkResponse userBookmarkResponse,
      Long userComments,
      Long userCourse) {

    String profileUrl = user.getUserInfo().getProfileUrl()
        .orElse(null);

    OtherUserDetailResponse otherUserDetail = OtherUserDetailResponse.builder()
        .id(user.getId())
        .email(user.getInfoNonChanged().getEmail())
        .nickname(user.getUserInfo().getNickname())
        .profileImage(profileUrl)
        .birth(fromLocalDate(user.getInfoNonChanged().getBirth()))
        .gender(user.getInfoNonChanged().getGender())
        .createdAt(fromLocalDateTime(user.getCreatedAt()))
        .build();

    UserCounts counts = UserCounts.builder()
        .course(userCourse)
        .comments(userComments)
        .bookmarks(userBookmarkResponse)
        .build();

    otherUserDetail.setCounts(counts);

    return otherUserDetail;
  }

  public static LoginResponse fromUserAndToken(User user, String token) {
    String profileUrl = user.getUserInfo().getProfileUrl()
        .orElse(null);

    LoginResponse response = LoginResponse.builder()
        .accessToken(token)
        .build();

    LoginResponse.Datas data = response.new Datas(
        user.getId(),
        user.getUserInfo().getNickname(),
        profileUrl
    );
    response.setUser(data);
    return response;
  }

  public static UserLikeResponse toUserLike(Long placeLike, Long courseLike) {
    return UserLikeResponse.builder()
        .total(placeLike + courseLike)
        .placeLike(placeLike)
        .courseLike(courseLike).build();
  }

  public static UserCommentResponse toUserComment(Long placeComment, Long courseComment) {
    return UserCommentResponse.builder()
        .total(placeComment + courseComment)
        .placeComment(placeComment)
        .courseComment(courseComment)
        .build();
  }

  public static UserBookmarkResponse toUserBookmark(Long placeBookmark, Long courseBookmark) {
    return UserBookmarkResponse.builder()
        .total(placeBookmark + courseBookmark)
        .placeBookmark(placeBookmark)
        .courseBookmark(courseBookmark)
        .build();
  }

  private static String fromLocalDate(LocalDate date) {
    return String.valueOf(date);
  }

  private static String fromLocalDateTime(LocalDateTime date) {
    return String.valueOf(date);
  }
}
