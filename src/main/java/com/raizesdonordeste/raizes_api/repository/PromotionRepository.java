package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("""
    SELECT p FROM Promotion p 
    WHERE p.product.id = :productId
    AND :today BETWEEN p.startDate AND p.endDate
    """)
    Promotion findActivePromotion(@Param("productId") Long productId, @Param("today") LocalDate today);

}
