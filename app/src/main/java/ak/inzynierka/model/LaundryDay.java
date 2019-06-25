package ak.inzynierka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LaundryDay implements Serializable {
  @JsonProperty("dayId")
  private long dayId;
  @JsonProperty("date")
  private String date;
  @JsonProperty("hour8")
  private String hour8;
  @JsonProperty("hour9")
  private String hour9;
  @JsonProperty("hour10")
  private String hour10;
  @JsonProperty("hour11")
  private String hour11;
  @JsonProperty("hour12")
  private String hour12;
  @JsonProperty("hour13")
  private String hour13;
  @JsonProperty("hour14")
  private String hour14;
  @JsonProperty("hour15")
  private String hour15;
  @JsonProperty("hour16")
  private String hour16;
  @JsonProperty("hour17")
  private String hour17;
  @JsonProperty("hour18")
  private String hour18;
  @JsonProperty("hour19")
  private String hour19;
  @JsonProperty("hour20")
  private String hour20;
  @JsonProperty("hour21")
  private String hour21;
  @JsonProperty("laundry")
  private LaundryRoom laundry;
}
