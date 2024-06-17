package org.rbernalop.pruebatecnicagft.price.infrastructure.persistence;

import org.mapstruct.Mapper;
import org.rbernalop.pruebatecnicagft.price.domain.Price;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
  Price toDomain(PriceEntity priceEntity);

  default Optional<Price> toOptionalDomain(Optional<PriceEntity> optionalPriceEntity) {
    return optionalPriceEntity.map(this::toDomain);
  }
}
