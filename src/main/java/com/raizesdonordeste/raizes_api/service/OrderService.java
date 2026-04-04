package com.raizesdonordeste.raizes_api.service;

import com.raizesdonordeste.raizes_api.dto.*;
import com.raizesdonordeste.raizes_api.entity.*;
import com.raizesdonordeste.raizes_api.enums.OrderStatus;
import com.raizesdonordeste.raizes_api.repository.*;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Listar todos os pedidos
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    // Buscar um pedido por ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
    // Buscar um pedido por Status
    public List<Order> getOrderByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    // Criar um novo pedido
    public OrderResponseDTO createOrder(CreateOrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.RECEIVED);
        order.setChannel(dto.getChannel());

        double total = 0;

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            double price = product.getPrice();
            int quantity = itemDTO.getQuantity();

            double subtotal = price * quantity;
            total += subtotal;

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setOrder(order);

            items.add(item);
        }

        order.setItems(items);
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(savedOrder.getId());
        response.setStatus(savedOrder.getStatus().name());
        response.setTotal(savedOrder.getTotal());

        return response;
    }

    // Atualizar o status de um pedido
    public Order updateOrderStatus(Long id, OrderStatus status) {
        
        Order order = orderRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

}
