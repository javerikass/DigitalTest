package ru.digitalchief.test.exception;

import org.springframework.http.HttpStatus;

public class PlayCharacterException extends RuntimeException {

  private final int status;

  public PlayCharacterException(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public static PlayCharacterException getPlayCharacterException(String message,
      HttpStatus status) {
    return new PlayCharacterException(message,
        status.value());
  }

}
