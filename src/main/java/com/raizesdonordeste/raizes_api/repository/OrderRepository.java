package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Order;
import com.raizesdonordeste.raizes_api.enums.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByStatus(OrderStatus status);

    @Query("""
        SELECT o.channel, COUNT(o)
        FROM Order o
        GROUP BY o.channel
    """)
    List<Object[]> getOrdersByChannel();

    @Query("""
        SELECT DATE(o.orderDate), SUM(o.total)
        FROM Order o
        GROUP BY DATE(o.orderDate)
    """)
    List<Object[]> getDailySales();

}
