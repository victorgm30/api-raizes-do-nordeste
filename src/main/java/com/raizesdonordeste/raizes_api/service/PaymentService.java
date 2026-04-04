package com.raizesdonordeste.raizes_api.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(Double amount) {
        // Lógica de processamento de pagamento
        // Para este exemplo, vamos simular um pagamento bem-sucedido
        return true; // Retorna true se o pagamento for processado com sucesso
    }

}
