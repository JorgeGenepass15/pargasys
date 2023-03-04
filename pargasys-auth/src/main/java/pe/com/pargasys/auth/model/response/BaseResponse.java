package pe.com.pargasys.auth.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseResponse {

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String message;

}
