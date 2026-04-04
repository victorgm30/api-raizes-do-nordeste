package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

     @Query("""
        SELECT oi.product.name, SUM(oi.quantity), SUM(oi.quantity * oi.price)
        FROM OrderItem oi
        GROUP BY oi.product.name
    """)
    List<Object[]> getSalesReport();

}
