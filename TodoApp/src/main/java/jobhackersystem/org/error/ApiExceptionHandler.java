package jobhackersystem.org.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {TodoNotFoundException.class})
    public ResponseEntity<Object> handleTodoNotFoundException(TodoNotFoundException ex){
        HttpStatus httpStatus=HttpStatus.NOT_FOUND;
        ApiBodyException exception=new ApiBodyException(ex.getMessage(), LocalDateTime.now(), httpStatus);
        return new ResponseEntity<>(exception,httpStatus);
    }
    @ExceptionHandler(value = {TodoDuplicateIDException.class})
    public ResponseEntity<Object> handleTodoDuplicateIDException(TodoDuplicateIDException ex){
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApiBodyException exception=new ApiBodyException(ex.getMessage(), LocalDateTime.now(), httpStatus);
        return new ResponseEntity<>(exception,httpStatus);
    }

}
