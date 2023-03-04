package pe.com.pargasys.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.com.pargasys.auth.constant.Constant;
import pe.com.pargasys.auth.model.response.Response;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> handleException(final Exception ex) {
        log.info("error_message: {}", ex.getMessage());
        log.info("error_stacktrace: {}", ex.getStackTrace());
        return new ResponseEntity<>(Response.handle(Constant.CODE_SERVER_ERROR, Constant.MESSAGE_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Response> handleUnauthorized(final UnauthorizedException ex) {
        log.info("error_message: {}", ex.getMessage());
        log.info("error_stacktrace: {}", ex.getStackTrace());
        return new ResponseEntity<>(Response.handle(Constant.CODE_UNAUTHORIZED, Constant.MESSAGE_UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

}
