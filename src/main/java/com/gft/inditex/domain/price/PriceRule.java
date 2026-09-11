package com.gft.inditex.domain.price;

import com.gft.inditex.infrastructure.persistence.jpa.Price;

import java.time.LocalDateTime;

public record PriceRule(
        Long brandId,
        Long productId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String currency,
        Integer priority
) {
    public static PriceRule fromEntity(Price price) {
        return new PriceRule(
                price.getBrand() != null ? price.getBrand().getId() : null,
                price.getProductId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency(),
                price.getPriority()
        );
    }
}
