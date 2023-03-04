package pe.com.pargasys.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class BaseResponse {

    @JsonProperty
    private Integer code;

    @JsonProperty
    private String message;

}
