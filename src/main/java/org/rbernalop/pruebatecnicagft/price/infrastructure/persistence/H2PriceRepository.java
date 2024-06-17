package org.rbernalop.pruebatecnicagft.price.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.rbernalop.pruebatecnicagft.price.domain.Price;
import org.rbernalop.pruebatecnicagft.price.domain.repository.PriceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class H2PriceRepository implements PriceRepository {
  private final JpaPriceRepository jpaPriceRepository;
  private final PriceEntityMapper priceEntityMapper;

  @Override
  public Optional<Price> findByTimeProductAndBrand(LocalDateTime applicationDateTime, int productId, int brandId) {
    Optional<PriceEntity> price = jpaPriceRepository.findByTimeProductAndBrand(applicationDateTime, productId, brandId);
    return priceEntityMapper.toOptionalDomain(price);
  }
}
