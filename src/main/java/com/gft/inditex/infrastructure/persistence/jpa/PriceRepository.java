package com.gft.inditex.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brand.id = :brandId AND p.productId = :productId AND :date BETWEEN p.startDate AND p.endDate")
    List<Price> findProductPrice(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("date") LocalDateTime date);
}
