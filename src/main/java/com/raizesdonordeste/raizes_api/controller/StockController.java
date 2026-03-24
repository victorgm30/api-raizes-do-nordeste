package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Stock;
import com.raizesdonordeste.raizes_api.repository.StockRepository;
import com.raizesdonordeste.raizes_api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    // Listar todos os estoques
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    // Obter um estoque por ID
    @GetMapping("/{id}")
    public Stock getStockById(@PathVariable Long id) {
        return stockRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
    }

    // Criar um novo estoque
    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    // Atualizar um estoque existente
    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
        Stock stock = stockRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        stock.setQuantity(stockDetails.getQuantity());
        stock.setProduct(stockDetails.getProduct());

        return stockRepository.save(stock);
    }

    // Deletar um estoque
    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        
       Stock stock = stockRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

        if (stock.getQuantity() > 0) {
            throw new RuntimeException("Stock has products associated and cannot be deleted");
        }

        stockRepository.deleteById(id);
    }
}
