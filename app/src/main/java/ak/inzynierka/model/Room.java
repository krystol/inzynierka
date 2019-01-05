package ak.inzynierka.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Room  implements Serializable {
  private int id;
  private RoomType roomType;
  private boolean isOccupied;
  private int keyInRoomNumber;
  private User occupiedByUser;
}
