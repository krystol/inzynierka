package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Room  implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("roomName")
  private String roomName;
  @JsonProperty("isOccupied")
  private boolean isOccupied;
  @JsonProperty("keyInRoomNumber")
  private Integer keyInRoomNumber;
  @JsonProperty("roomNumber")
  private Integer roomNumber;
  @JsonProperty("occupiedByUser")
  private User occupiedByUser;
}
