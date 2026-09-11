package com.gft.inditex.application.price;

import com.gft.inditex.domain.price.PriceRule;

import java.time.LocalDateTime;

public record PriceQueryResponse(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        String currency
) {
    public static PriceQueryResponse fromRule(PriceRule rule) {
        return new PriceQueryResponse(
                rule.productId(),
                rule.brandId(),
                rule.priceList(),
                rule.startDate(),
                rule.endDate(),
                rule.price(),
                rule.currency()
        );
    }
}
