package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RoomType  implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("roomName")
  private String roomName;
  private Set<Room> rooms;
}
