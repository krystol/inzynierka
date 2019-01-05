
package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BoardMessage implements Serializable{
  @JsonProperty("id")
  private int id;
  @JsonProperty("author")
  private User author;
  @JsonProperty("title")
  private String title;
  @JsonProperty("message")
  private String message;
  @JsonProperty("date")
  private Date date;
}
