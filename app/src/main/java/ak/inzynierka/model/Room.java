package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Room  implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("roomType")
  private RoomType roomType;
  @JsonProperty("isOccupied")
  private boolean isOccupied;
  @JsonProperty("keyInRoomNumber")
  private int keyInRoomNumber;
  @JsonProperty("occupiedByUser")
  private User occupiedByUser;
}
