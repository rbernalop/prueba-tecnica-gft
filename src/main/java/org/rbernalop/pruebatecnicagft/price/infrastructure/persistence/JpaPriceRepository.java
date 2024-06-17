package org.rbernalop.pruebatecnicagft.price.infrastructure.persistence;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query(value = "SELECT * FROM PRICES p "
      + "WHERE p.product_id = :productId "
      + "AND p.brand_id = :brandId "
      + "AND :applicationDateTime BETWEEN p.start_date AND p.end_date "
      + "ORDER BY p.priority DESC "
      + "LIMIT 1",
      nativeQuery = true)
  Optional<PriceEntity> findByTimeProductAndBrand(
      @Param("applicationDateTime") LocalDateTime applicationDateTime,
      @Param("productId") int productId,
      @Param("brandId") int brandId
  );
}
