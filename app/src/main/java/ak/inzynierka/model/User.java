package ak.inzynierka.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class User  implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("livingInRoomNumber")
  private int livingInRoomNumber;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  private Set<BoardMessage> boardMessages;
  private Set<Room> occupiedRooms;
}
