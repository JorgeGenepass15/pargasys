package pe.com.pargasys.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

}
