package ak.inzynierka.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class User  implements Serializable {
  private int id;
  private int livingInRoomNumber;
  private String firstName;
  private String lastName;
  private Set<BoardMessage> boardMessages;
  private Set<Room> occupiedRooms;
}
