package ak.inzynierka.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class RoomType  implements Serializable {
  private int id;
  private String roomName;
  private Set<Room> rooms;
}
