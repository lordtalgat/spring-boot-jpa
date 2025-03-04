package talgat.home.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUnmatchedExceptions(Exception ex) {
        ResponseStatus responseStatus =
                AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus == null || HttpStatus.INTERNAL_SERVER_ERROR.equals(responseStatus.code())) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ex.getMessage(), responseStatus.code());
    }
}