package inc.codeman.springboot.todomanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskNotFoundException exception) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setError("TASK_NOT_FOUND");
    errorResponse.setStatus(404);
    errorResponse.setMessage(exception.getMessage());
    errorResponse.setDateTime(LocalDateTime.now());
    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
  }

}
