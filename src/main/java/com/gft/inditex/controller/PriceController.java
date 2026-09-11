package com.gft.inditex.controller;

import com.gft.inditex.model.dto.PriceDTO;
import com.gft.inditex.model.dto.SearchFilterDTO;
import com.gft.inditex.service.PriceService;
import com.gft.inditex.service.exception.InditexValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PriceController {

    private final PriceService priceService;

    @Operation(summary = "Get applicable product price", description = "Returns the best matching price for the provided brand, product and application date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Price found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "No price found")
    })
    @GetMapping("/prices")
    public ResponseEntity<PriceDTO> getApplicablePrice(
            @Parameter(description = "Application date in ISO-8601 format", example = "2020-06-14T16:00:00")
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @Parameter(description = "Product identifier", example = "35455")
            @RequestParam("productId") Long productId,
            @Parameter(description = "Brand identifier", example = "1")
            @RequestParam("brandId") Long brandId) throws InditexValidationException {

        SearchFilterDTO searchFilter = SearchFilterDTO.builder()
                .brandId(brandId)
                .productId(productId)
                .date(applicationDate)
                .build();

        return ResponseEntity.ok(priceService.findProductPrice(searchFilter));
    }
}
