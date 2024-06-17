package org.rbernalop.pruebatecnicagft.price.infrastructure.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.rbernalop.pruebatecnicagft.price.application.finder.FindPricesResponse;
import org.rbernalop.pruebatecnicagft.price.application.finder.PriceFinder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceGetController {
  private final PriceFinder priceFinder;

  @GetMapping("api/v1/price")
  public FindPricesResponse getPrice(
      @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime applicationDateTime,
      @RequestParam int productId,
      @RequestParam int brandId) {
    return priceFinder.findProductPriceByTimeAndBrand(applicationDateTime, productId, brandId);
  }
}
