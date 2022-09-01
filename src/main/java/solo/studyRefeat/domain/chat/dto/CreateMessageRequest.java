package solo.studyRefeat.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMessageRequest {

  private String message;

  private Long chatRoomId;

}
