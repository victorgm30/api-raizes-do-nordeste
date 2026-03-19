package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
