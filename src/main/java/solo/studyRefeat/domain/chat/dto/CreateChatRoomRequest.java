package solo.studyRefeat.domain.chat.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChatRoomRequest {

  private List<Long> userIds = new ArrayList<>();

  private String roomName;
}
