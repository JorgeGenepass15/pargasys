package pe.com.pargasys.auth.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pe.com.pargasys.auth.constant.Constant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> extends BaseResponse {

    @JsonProperty
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response();
        response.setCode(Constant.CODE_OK);
        response.setMessage(Constant.MESSAGE_OK);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> handle(Integer code, String message) {
        Response<T> response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
