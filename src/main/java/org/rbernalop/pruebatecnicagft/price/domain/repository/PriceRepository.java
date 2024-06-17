package org.rbernalop.pruebatecnicagft.price.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.rbernalop.pruebatecnicagft.price.domain.Price;

public interface PriceRepository {
  Optional<Price> findByTimeProductAndBrand(LocalDateTime applicationDateTime, int productId, int brandId);
}
