package com.gft.inditex.infrastructure.persistence.jpa;

import com.gft.inditex.application.port.PriceQueryPort;
import com.gft.inditex.domain.price.PriceRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceQueryPort {

    private final PriceRepository priceRepository;

    @Override
    public Optional<PriceRule> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepository.findProductPrice(brandId, productId, applicationDate)
                .stream()
                .map(PriceRule::fromEntity)
                .max(Comparator.comparingInt(PriceRule::priority));
    }
}
