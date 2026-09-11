package com.gft.inditex.service;

import com.gft.inditex.model.dto.PriceDTO;
import com.gft.inditex.model.dto.SearchFilterDTO;
import com.gft.inditex.model.h2.Price;
import com.gft.inditex.repository.PriceRepository;
import com.gft.inditex.service.exception.InditexValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceDTO findProductPrice(SearchFilterDTO searchFilterDTO) throws InditexValidationException {

        List<Price> productPrices = priceRepository.findProductPrice(searchFilterDTO.getBrandId(), searchFilterDTO.getProductId(), searchFilterDTO.getDate());

        if(CollectionUtils.isEmpty(productPrices)) {
            throw new InditexValidationException("The product doesn't exist.", HttpStatus.NOT_FOUND);
        }

        return productPrices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .map(p -> PriceDTO.builder()
                        .productId(p.getProductId())
                        .brandId(p.getBrand().getId())
                        .priceList(p.getPriceList())
                        .startDate(p.getStartDate())
                        .endDate(p.getEndDate())
                        .price(p.getPrice())
                        .currency(p.getCurrency())
                        .build())
                .orElse(null);
    }
}
