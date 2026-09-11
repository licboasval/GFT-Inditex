package com.gft.inditex.unit;

import com.gft.inditex.model.dto.PriceDTO;
import com.gft.inditex.model.dto.SearchFilterDTO;
import com.gft.inditex.service.PriceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PriceServiceTest {

    @Autowired
    private PriceServiceImpl priceService;


    @ParameterizedTest
    @MethodSource("productPriceTestArguments")
    @DisplayName("Test product price service")
    void testProductPriceService(SearchFilterDTO filter, PriceDTO expectedResult) throws Exception {

        PriceDTO productPrice = priceService.findProductPrice(filter);

        assertThat(productPrice.getProductId()).isEqualTo(expectedResult.getProductId());
        assertThat(productPrice.getBrandId()).isEqualTo(expectedResult.getBrandId());
        assertThat(productPrice.getPriceList()).isEqualTo(expectedResult.getPriceList());
        assertThat(productPrice.getStartDate()).isEqualTo(expectedResult.getStartDate());
        assertThat(productPrice.getEndDate()).isEqualTo(expectedResult.getEndDate());
        assertThat(productPrice.getPrice()).isEqualTo(expectedResult.getPrice());
        assertThat(productPrice.getCurrency()).isEqualTo(expectedResult.getCurrency());

    }

    static Stream<Arguments> productPriceTestArguments() {
        return Stream.of(
                Arguments.of(SearchFilterDTO.builder().productId(35455L).brandId(1L).date(LocalDateTime.parse("2020-06-14T10:00:00")).build(),
                        PriceDTO.builder().productId(35455L).brandId(1L).priceList(1).startDate(LocalDateTime.parse("2020-06-14T00:00:00")).endDate(LocalDateTime.parse("2020-12-31T23:59:59")).price(35.50).currency("EUR").build()),
                Arguments.of(SearchFilterDTO.builder().productId(35455L).brandId(1L).date(LocalDateTime.parse("2020-06-14T16:00:00")).build(),
                        PriceDTO.builder().productId(35455L).brandId(1L).priceList(2).startDate(LocalDateTime.parse("2020-06-14T15:00:00")).endDate(LocalDateTime.parse("2020-06-14T18:30:00")).price(25.45).currency("EUR").build()),
                Arguments.of(SearchFilterDTO.builder().productId(35455L).brandId(1L).date(LocalDateTime.parse("2020-06-14T21:00:00")).build(),
                        PriceDTO.builder().productId(35455L).brandId(1L).priceList(1).startDate(LocalDateTime.parse("2020-06-14T00:00:00")).endDate(LocalDateTime.parse("2020-12-31T23:59:59")).price(35.50).currency("EUR").build()),
                Arguments.of(SearchFilterDTO.builder().productId(35455L).brandId(1L).date(LocalDateTime.parse("2020-06-15T10:00:00")).build(),
                        PriceDTO.builder().productId(35455L).brandId(1L).priceList(3).startDate(LocalDateTime.parse("2020-06-15T00:00:00")).endDate(LocalDateTime.parse("2020-06-15T11:00:00")).price(30.50).currency("EUR").build()),
                Arguments.of(SearchFilterDTO.builder().productId(35455L).brandId(1L).date(LocalDateTime.parse("2020-06-16T21:00:00")).build(),
                        PriceDTO.builder().productId(35455L).brandId(1L).priceList(4).startDate(LocalDateTime.parse("2020-06-15T16:00:00")).endDate(LocalDateTime.parse("2020-12-31T23:59:59")).price(38.95).currency("EUR").build())
        );
    }
}
