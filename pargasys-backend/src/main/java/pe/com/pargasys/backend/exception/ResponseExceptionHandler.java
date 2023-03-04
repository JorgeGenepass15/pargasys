package pe.com.pargasys.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.com.pargasys.backend.constant.Constant;
import pe.com.pargasys.backend.model.response.Response;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> handleException(final Exception ex) {
        log.info("error_message: {}", ex.getMessage());
        log.info("error_stacktrace: {}", ex.getStackTrace());
        return new ResponseEntity<>(Response.handle(Constant.CODE_SERVER_ERROR, Constant.MESSAGE_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
