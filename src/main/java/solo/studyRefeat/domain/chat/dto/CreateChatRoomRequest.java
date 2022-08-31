package solo.studyRefeat.domain.chat.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChatRoomRequest {

  @Builder.Default
  private List<Long> userIds = new ArrayList<>();

  private String roomName;
}
