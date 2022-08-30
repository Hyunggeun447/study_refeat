package solo.studyRefeat.domain.chat.dto;

import lombok.Getter;

@Getter
public class AddChatUserRequest {

  private Long chatRoomId;

  private Long userId;

}
