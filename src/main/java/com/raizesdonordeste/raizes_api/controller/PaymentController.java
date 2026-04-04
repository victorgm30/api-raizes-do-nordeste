package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.*;
import com.raizesdonordeste.raizes_api.enums.OrderStatus;
import com.raizesdonordeste.raizes_api.enums.PaymentStatus;
import com.raizesdonordeste.raizes_api.repository.PaymentRepository;
import com.raizesdonordeste.raizes_api.repository.OrderRepository;
import com.raizesdonordeste.raizes_api.service.PaymentService;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;
    
    // Listar todos os pagamentos
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    // Buscar pagamento por ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
    }

    // Criar um novo pagamento manualmente
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    // Atualizar o status de um pagamento existente
    @PatchMapping("/{id}/status")
    public Payment updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        
        Payment payment = getPaymentById(id);
                
        payment.setStatus(PaymentStatus.valueOf(status.toUpperCase()));
        
        return paymentRepository.save(payment);
    }

    // Endpoint utilizado para simular integração com gateway de pagamento
    // Processar um pagamento (MOCK) para um pedido
    @PostMapping("/process/{orderId}")
    public Payment processPayment(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        
        boolean approved = paymentService.processPayment(order.getTotal());
        
        Payment payment = new Payment();
        payment.setOrder(order);

        if(approved) {
            payment.setStatus(PaymentStatus.APPROVED);
            order.setStatus(OrderStatus.PAID);
        } else {
            payment.setStatus(PaymentStatus.REFUSED);
        }
        
        orderRepository.save(order);

        return paymentRepository.save(payment);
    }    
}
