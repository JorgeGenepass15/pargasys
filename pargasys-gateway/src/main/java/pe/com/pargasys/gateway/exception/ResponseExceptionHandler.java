package pe.com.pargasys.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.com.pargasys.gateway.constant.Constant;
import pe.com.pargasys.gateway.model.response.Response;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Mono<Response>> handleException(final Exception ex) {
        log.info("error_message: {}", ex.getMessage());
        log.info("error_stacktrace: {}", ex.getStackTrace());
        return new ResponseEntity<>(Mono.just(Response.handle(Constant.CODE_SERVER_ERROR, Constant.MESSAGE_SERVER_ERROR)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Mono<Response>> handleBadRequest(final BadRequestException ex) {
        log.info("error_message: {}", ex.getMessage());
        log.info("error_stacktrace: {}", ex.getStackTrace());
        return new ResponseEntity<>(Mono.just(Response.handle(Constant.CODE_BAD_REQUEST, Constant.MESSAGE_BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

}
