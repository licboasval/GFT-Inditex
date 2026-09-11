package com.gft.inditex.integration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("priceCases")
    void shouldReturnApplicablePrice(String applicationDate, Long brandId, Long productId, Integer expectedPriceList, Double expectedPrice) throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("applicationDate", applicationDate)
                        .param("brandId", String.valueOf(brandId))
                        .param("productId", String.valueOf(productId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.priceList").value(expectedPriceList))
                .andExpect(jsonPath("$.price").value(expectedPrice))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    private static Stream<Arguments> priceCases() {
        return Stream.of(
                Arguments.of("2020-06-14T10:00:00", 1L, 35455L, 1, 35.50),
                Arguments.of("2020-06-14T16:00:00", 1L, 35455L, 2, 25.45),
                Arguments.of("2020-06-14T21:00:00", 1L, 35455L, 1, 35.50),
                Arguments.of("2020-06-15T10:00:00", 1L, 35455L, 3, 30.50),
                Arguments.of("2020-06-16T21:00:00", 1L, 35455L, 4, 38.95)
        );
    }
}
