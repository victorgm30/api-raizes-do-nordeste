package com.raizesdonordeste.raizes_api.service;

import com.raizesdonordeste.raizes_api.repository.OrderRepository;
import com.raizesdonordeste.raizes_api.repository.StockRepository;
import com.raizesdonordeste.raizes_api.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Object[]> getOrdersByChannel() {
        return orderRepository.getOrdersByChannel();
    }

    public List<Object[]> getSalesReport() {
        return orderItemRepository.getSalesReport();
    }

    public List<Object[]> getStockReport() {
        return stockRepository.getStockReport();
    }

}
