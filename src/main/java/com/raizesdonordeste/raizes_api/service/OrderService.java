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
import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        order.setCustomer(customer);        

        double total = 0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            double price = product.getPrice();

            // Verificar se há promoção ativa para o produto
            Promotion promotion = promotionRepository.findActivePromotion(product.getId(), LocalDate.now());
            
            if (promotion != null) {
                price = price * (1 - promotion.getDiscountPercentage() / 100);
            }

            double subtotal = price * itemDTO.getQuantity();
            total += subtotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(price);
            orderItem.setOrder(order);

            orderItems.add(orderItem);

        }

            order.setItems(orderItems);
            order.setTotal(total);
            
            Order saveOrder = orderRepository.save(order);

            OrderResponseDTO responseDTO = new OrderResponseDTO();
            responseDTO.setId(saveOrder.getId());
            responseDTO.setStatus(saveOrder.getStatus().name());
            responseDTO.setTotal(saveOrder.getTotal());

            return responseDTO;
    }

    // Atualizar o status de um pedido
    public Order updateOrderStatus(Long id, OrderStatus status) {
        
        Order order = orderRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(status);
        return orderRepository.save(order);
    }

}
