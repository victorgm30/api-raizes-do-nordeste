package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("""
        SELECT s.product.name, s.quantity
        FROM Stock s
    """)
    List<Object[]> getStockReport();

}

