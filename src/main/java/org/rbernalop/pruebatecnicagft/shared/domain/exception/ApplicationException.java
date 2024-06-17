package org.rbernalop.pruebatecnicagft.shared.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class ApplicationException extends RuntimeException {
  private final ApplicationError error;

  @Override
  public String getMessage() {
    return error.getMessage();
  }

  public HttpStatus getStatus() {
    return error.getStatus();
  }
}
