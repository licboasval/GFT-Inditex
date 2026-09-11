package com.gft.inditex.service.validators;

import com.gft.inditex.model.dto.SearchFilterDTO;
import com.gft.inditex.service.exception.InditexValidationException;
import org.springframework.stereotype.Component;

@Component
public class InditexValidator {
    public void assertProductPriceFilter(SearchFilterDTO filter) throws InditexValidationException {
        if (filter.getProductId() == null || filter.getBrandId() == null || filter.getDate() == null) {
            throw new InditexValidationException("One or many of the required fields are missing.");
        }
    }
}
