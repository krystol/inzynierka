package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LaundryRoom extends Room implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("roomName")
  private String roomName;
  @JsonProperty("keyInRoomNumber")
  private Integer keyInRoomNumber;
  @JsonProperty("roomNumber")
  private Integer roomNumber;
}
