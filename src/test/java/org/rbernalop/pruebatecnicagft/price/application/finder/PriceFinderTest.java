package org.rbernalop.pruebatecnicagft.price.application.finder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rbernalop.pruebatecnicagft.price.application.ProductMapper;
import org.rbernalop.pruebatecnicagft.price.domain.Price;
import org.rbernalop.pruebatecnicagft.price.domain.PriceMother;
import org.rbernalop.pruebatecnicagft.price.domain.repository.PriceRepository;
import org.rbernalop.pruebatecnicagft.shared.domain.exception.ApplicationError;
import org.rbernalop.pruebatecnicagft.shared.domain.exception.ApplicationException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceFinderTest {
  @Mock
  private PriceRepository priceRepository;

  private PriceFinder priceFinder;

  @BeforeEach
  void setUp() {
    priceFinder = new PriceFinder(priceRepository, Mappers.getMapper(ProductMapper.class));
  }

  @Test
  void shouldReturnPrice() {
    // GIVEN
    LocalDateTime accessDate = LocalDateTime.now();
    int brandId = 1;
    int productId = 2;

    Price expectedPrice = PriceMother.randomFromProductAndBrandId(productId, brandId);

    when(priceRepository.findByTimeProductAndBrand(accessDate, productId, brandId))
        .thenReturn(Optional.of(expectedPrice));

    // WHEN
    FindPricesResponse actualPrice =
        assertDoesNotThrow(() -> priceFinder.findProductPriceByTimeAndBrand(accessDate, productId, brandId));

    // THEN
    assertEquals(expectedPrice.getAmount(), actualPrice.getPrice());
    assertEquals(expectedPrice.getBrandId(), actualPrice.getBrandId());
    assertEquals(expectedPrice.getProductId(), actualPrice.getProductId());
    assertEquals(expectedPrice.getStartDate(), actualPrice.getStartDate());
    assertEquals(expectedPrice.getEndDate(), actualPrice.getEndDate());
    verify(priceRepository, times(1)).findByTimeProductAndBrand(accessDate, productId, brandId);
  }

  @Test
  void shouldThrowExceptionWhenPriceNotFound() {
    // GIVEN
    LocalDateTime accessDate = LocalDateTime.now();
    int brandId = 1;
    int productId = 2;

    // WHEN
    ApplicationException exception =
        assertThrows(ApplicationException.class, () -> priceFinder.findProductPriceByTimeAndBrand(accessDate, productId, brandId));

    // THEN
    assertEquals(ApplicationError.PRICE_NOT_FOUND.getMessage(), exception.getMessage());
    assertEquals(ApplicationError.PRICE_NOT_FOUND.getStatus(), exception.getStatus());
    verify(priceRepository, times(1)).findByTimeProductAndBrand(accessDate, productId, brandId);
  }
}