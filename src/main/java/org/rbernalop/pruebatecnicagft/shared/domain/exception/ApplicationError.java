package org.rbernalop.pruebatecnicagft.shared.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ApplicationError {
  PRICE_NOT_FOUND("The price could not be found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus status;
}
