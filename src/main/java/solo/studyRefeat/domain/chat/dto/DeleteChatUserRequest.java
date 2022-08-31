package solo.studyRefeat.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteChatUserRequest {

  private Long chatRoomId;

  private Long userId;

}
