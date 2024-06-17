package org.rbernalop.pruebatecnicagft.price.application.finder;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.pruebatecnicagft.price.application.ProductMapper;
import org.rbernalop.pruebatecnicagft.price.domain.Price;
import org.rbernalop.pruebatecnicagft.price.domain.repository.PriceRepository;
import org.rbernalop.pruebatecnicagft.shared.domain.exception.ApplicationError;
import org.rbernalop.pruebatecnicagft.shared.domain.exception.ApplicationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceFinder {

  private final PriceRepository priceRepository;
  private final ProductMapper productMapper;

  public FindPricesResponse findProductPriceByTimeAndBrand(LocalDateTime applicationDateTime, int productId, int brandId) {
    log.info("Finding product {} price from brand {} at {}", productId, brandId, applicationDateTime);
    Optional<Price> price = priceRepository.findByTimeProductAndBrand(applicationDateTime, productId, brandId);

    if(price.isEmpty()) {
      throw new ApplicationException(ApplicationError.PRICE_NOT_FOUND);
    }

    return productMapper.toResponse(price.get());
  }
}
