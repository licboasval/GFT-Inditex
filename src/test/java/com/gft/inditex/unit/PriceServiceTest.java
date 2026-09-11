package com.gft.inditex.unit;

import com.gft.inditex.application.exception.InditexValidationException;
import com.gft.inditex.application.price.PriceFinderServiceImpl;
import com.gft.inditex.application.price.PriceQueryRequest;
import com.gft.inditex.application.price.PriceQueryResponse;
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
    private PriceFinderServiceImpl priceService;

    @ParameterizedTest
    @MethodSource("productPriceTestArguments")
    @DisplayName("Test product price service")
    void testProductPriceService(PriceQueryRequest request, PriceQueryResponse expectedResult) throws InditexValidationException {
        PriceQueryResponse productPrice = priceService.findApplicablePrice(request);

        assertThat(productPrice.productId()).isEqualTo(expectedResult.productId());
        assertThat(productPrice.brandId()).isEqualTo(expectedResult.brandId());
        assertThat(productPrice.priceList()).isEqualTo(expectedResult.priceList());
        assertThat(productPrice.startDate()).isEqualTo(expectedResult.startDate());
        assertThat(productPrice.endDate()).isEqualTo(expectedResult.endDate());
        assertThat(productPrice.price()).isEqualTo(expectedResult.price());
        assertThat(productPrice.currency()).isEqualTo(expectedResult.currency());
    }

    static Stream<Arguments> productPriceTestArguments() {
        return Stream.of(
                Arguments.of(new PriceQueryRequest(1L, 35455L, LocalDateTime.parse("2020-06-14T10:00:00")),
                        new PriceQueryResponse(35455L, 1L, 1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 35.50, "EUR")),
                Arguments.of(new PriceQueryRequest(1L, 35455L, LocalDateTime.parse("2020-06-14T16:00:00")),
                        new PriceQueryResponse(35455L, 1L, 2, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 25.45, "EUR")),
                Arguments.of(new PriceQueryRequest(1L, 35455L, LocalDateTime.parse("2020-06-14T21:00:00")),
                        new PriceQueryResponse(35455L, 1L, 1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 35.50, "EUR")),
                Arguments.of(new PriceQueryRequest(1L, 35455L, LocalDateTime.parse("2020-06-15T10:00:00")),
                        new PriceQueryResponse(35455L, 1L, 3, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 30.50, "EUR")),
                Arguments.of(new PriceQueryRequest(1L, 35455L, LocalDateTime.parse("2020-06-16T21:00:00")),
                        new PriceQueryResponse(35455L, 1L, 4, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 38.95, "EUR"))
        );
    }
}
