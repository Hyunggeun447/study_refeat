package solo.studyRefeat.domain.chat.dto;

import lombok.Getter;

@Getter
public class DeleteChatUserRequest {

  private Long ChatRoomId;

  private Long UserId;

}
