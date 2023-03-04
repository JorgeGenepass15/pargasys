package pe.com.pargasys.backend.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import pe.com.pargasys.backend.constant.Constant;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseList<T> extends BaseResponse {

    @JsonProperty
    private List<T> data;

    public static <T> ResponseList<T> success(List<T> data) {
        ResponseList<T> response = new ResponseList();
        response.setCode(Constant.CODE_OK);
        response.setMessage(Constant.MESSAGE_OK);
        response.setData(data);
        return response;
    }

}
