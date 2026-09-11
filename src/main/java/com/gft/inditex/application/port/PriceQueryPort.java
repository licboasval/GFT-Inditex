package com.gft.inditex.application.port;

import com.gft.inditex.domain.price.PriceRule;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceQueryPort {
    Optional<PriceRule> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
