package ru.digitalchief.test.exception;

import org.springframework.http.HttpStatus;

public class ItemException extends RuntimeException {

  private final int status;

  public ItemException(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public static ItemException getItemException(String message,
      HttpStatus status) {
    return new ItemException(message,
        status.value());
  }
}
