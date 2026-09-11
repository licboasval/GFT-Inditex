package com.gft.inditex.application.price;

import com.gft.inditex.application.exception.InditexValidationException;

public interface PriceFinderService {
    PriceQueryResponse findApplicablePrice(PriceQueryRequest request) throws InditexValidationException;
}
