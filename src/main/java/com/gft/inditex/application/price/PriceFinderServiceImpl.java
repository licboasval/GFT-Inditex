package com.gft.inditex.application.price;

import com.gft.inditex.application.exception.InditexValidationException;
import com.gft.inditex.application.port.PriceQueryPort;
import com.gft.inditex.domain.price.PriceRule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceFinderServiceImpl implements PriceFinderService {

    private final PriceQueryPort priceQueryPort;

    @Override
    public PriceQueryResponse findApplicablePrice(PriceQueryRequest request) throws InditexValidationException {
        Optional<PriceRule> rule = priceQueryPort.findApplicablePrice(
                request.brandId(),
                request.productId(),
                request.applicationDate()
        );

        return rule
                .map(PriceQueryResponse::fromRule)
                .orElseThrow(() -> new InditexValidationException("The product doesn't exist.", HttpStatus.NOT_FOUND));
    }
}
