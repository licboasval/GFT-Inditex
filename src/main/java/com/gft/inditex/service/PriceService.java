package com.gft.inditex.service;

import com.gft.inditex.model.dto.PriceDTO;
import com.gft.inditex.model.dto.SearchFilterDTO;
import com.gft.inditex.service.exception.InditexValidationException;


public interface PriceService {
    PriceDTO findProductPrice(SearchFilterDTO searchFilterDTO) throws InditexValidationException;
}
