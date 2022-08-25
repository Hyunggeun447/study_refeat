package solo.studyRefeat.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

  MALE("male"),
  FEMALE("female"),
  ;

  private final String value;

  Gender(String value) {
    this.value = value;
  }

  @JsonCreator
  public static Gender from(String value) {
    for (Gender status : Gender.values()) {
      if (status.getValue().equals(value)) {
        return status;
      }
    }
    return null;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}
