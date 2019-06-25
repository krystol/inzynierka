package ak.inzynierka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResult implements Serializable {

  private String name;

  @Builder.Default
  private List<String> roles = new ArrayList<>();

  private String token;
}