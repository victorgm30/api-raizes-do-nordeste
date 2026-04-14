package com.raizesdonordeste.raizes_api.service;

import com.raizesdonordeste.raizes_api.dto.CreateProductDTO;
import com.raizesdonordeste.raizes_api.entity.*;
import com.raizesdonordeste.raizes_api.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UnitRepository unitRepository;

    public Product createProduct(CreateProductDTO dto) {
        
        Product product = new Product();
        
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setActive(dto.getActive());

        //Buscar no banco de dados
        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));

        Unit unit = unitRepository.findById(dto.getUnitId())
            .orElseThrow(() -> new RuntimeException("Unit not found"));

        product.setCategory(category);
        product.setUnit(unit);
        
        return productRepository.save(product);

    }

}
