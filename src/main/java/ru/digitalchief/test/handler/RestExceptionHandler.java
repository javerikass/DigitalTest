package ru.digitalchief.test.handler;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.digitalchief.test.controller.response.ErrorResponse;
import ru.digitalchief.test.exception.ItemException;
import ru.digitalchief.test.exception.PlayCharacterException;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(ItemException.class)
  public ResponseEntity<ErrorResponse> handleItemException(ItemException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
  }

  @ExceptionHandler(PlayCharacterException.class)
  public ResponseEntity<ErrorResponse> handlePlayCharacterException(PlayCharacterException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getStatus(), ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatus()));
  }

}
