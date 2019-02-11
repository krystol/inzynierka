package ak.inzynierka.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class User  implements Serializable {
  @JsonProperty("id")
  private int id;
  @JsonProperty("livingInRoomNumber")
  private Integer livingInRoomNumber;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  private Set<BoardMessage> boardMessages;
  private Set<Room> occupiedRooms;

  public User(String firstName, String lastName, Integer livingInRoomNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.livingInRoomNumber = livingInRoomNumber;
  }
}
