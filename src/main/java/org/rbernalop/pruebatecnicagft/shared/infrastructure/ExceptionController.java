package org.rbernalop.pruebatecnicagft.shared.infrastructure;

import org.rbernalop.pruebatecnicagft.shared.domain.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<CustomError> handle(ApplicationException exception) {
    return ResponseEntity
        .status(exception.getStatus())
        .body(new CustomError(exception.getMessage()));
  }
}
