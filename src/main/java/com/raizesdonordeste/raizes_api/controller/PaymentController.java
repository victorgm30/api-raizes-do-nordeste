package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Payment;
import com.raizesdonordeste.raizes_api.repository.PaymentRepository;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;
import com.raizesdonordeste.raizes_api.entity.PaymentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    
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

    // Criar um novo pagamento
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
}
