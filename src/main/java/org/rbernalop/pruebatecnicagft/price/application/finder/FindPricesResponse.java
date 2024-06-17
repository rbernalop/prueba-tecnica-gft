package org.rbernalop.pruebatecnicagft.price.application.finder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FindPricesResponse {
  private Integer productId;
  private Integer brandId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private BigDecimal price;
}
