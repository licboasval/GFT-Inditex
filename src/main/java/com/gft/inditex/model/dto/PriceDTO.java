package com.gft.inditex.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO implements Serializable {

        @Schema(description = "Price identifier")
        private Long brandId;
        @Schema(description = "Product identifier")
        private Long productId;
        @Schema(description = "Price list identifier")
        private Integer priceList;
        @Schema(description = "Price")
        private Double price;
        @Schema(description = "Start date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime startDate;
        @Schema(description = "End date")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime endDate;
        @Schema(description = "Currency")
        private String currency;
}
