package com.raizesdonordeste.raizes_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raizesdonordeste.raizes_api.service.ReportService;

import java.util.List;

@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/sales")
    public List<Object[]> sales() {
        return reportService.getSalesReport();
    }

    @GetMapping("/channels")
    public List<Object[]> channels() {
        return reportService.getOrdersByChannel();
    }

    @GetMapping("/stock")
    public List<Object[]> stock() {
        return reportService.getStockReport();
    }
}
