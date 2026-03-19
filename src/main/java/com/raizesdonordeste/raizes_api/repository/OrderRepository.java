package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Order;
import com.raizesdonordeste.raizes_api.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

}
