package org.rbernalop.pruebatecnicagft.shared.infrastructure;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomError {
  final String message;
  LocalDateTime dateTime = LocalDateTime.now();
}
