package com.gft.inditex.application.price;

import java.time.LocalDateTime;

public record PriceQueryRequest(Long brandId, Long productId, LocalDateTime applicationDate) {
}
