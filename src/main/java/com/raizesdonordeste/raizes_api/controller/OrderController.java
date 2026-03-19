package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.dto.CreateOrderDTO;
import com.raizesdonordeste.raizes_api.dto.OrderResponseDTO;
import com.raizesdonordeste.raizes_api.entity.Order;
import com.raizesdonordeste.raizes_api.entity.OrderStatus;
import com.raizesdonordeste.raizes_api.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    // Listar todos os pedidos
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    // Buscar um pedido por ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    // Buscar um pedido por status
    @GetMapping("/status/{status}")
    public List<Order> getOrderByStatus(@PathVariable OrderStatus status) {
        return orderService.getOrderByStatus(status);
    }

    // Criar um novo pedido (DTO)
    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    // Atualizar o status de um pedido
    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }
}
