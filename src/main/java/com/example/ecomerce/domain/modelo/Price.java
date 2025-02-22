package com.example.ecomerce.domain.modelo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private Integer productId;
    private Integer brandId;
    private Integer priceListId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal salePrice;
    private String curr;

}
