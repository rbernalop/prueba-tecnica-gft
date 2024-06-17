package org.rbernalop.pruebatecnicagft.price.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rbernalop.pruebatecnicagft.price.application.finder.FindPricesResponse;
import org.rbernalop.pruebatecnicagft.price.domain.Price;

@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "amount", target = "price")
  FindPricesResponse toResponse(Price price);
}
