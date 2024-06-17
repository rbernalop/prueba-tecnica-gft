package org.rbernalop.pruebatecnicagft.price.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
public class PriceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "BRAND_ID")
  private Integer brandId;

  @Column(name = "START_DATE")
  private LocalDateTime startDate;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;

  @Column(name = "PRICE_LIST")
  private Integer priceList;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private BigDecimal amount;

  @Column(name = "CURR")
  private String curr;
}
