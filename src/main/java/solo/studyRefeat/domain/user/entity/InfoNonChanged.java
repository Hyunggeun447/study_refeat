package solo.studyRefeat.domain.user.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InfoNonChanged {

  @Column(name = "email")
  private String email;

  @Column(name = "birth")
  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender;
}
