package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.dto.CreateProductDTO;
import com.raizesdonordeste.raizes_api.entity.Product;
import com.raizesdonordeste.raizes_api.repository.ProductRepository;
import com.raizesdonordeste.raizes_api.repository.UnitRepository;
import com.raizesdonordeste.raizes_api.service.ProductService;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Define a classe como um controlador REST
@RequestMapping("/products") //Cria um endpoint para acessar os produtos
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    //Listar todos os produtos
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    //Buscar um produto por ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    //Inserir um novo produto
    @PostMapping
    public Product create(@RequestBody CreateProductDTO dto) {
        return productService.createProduct(dto);
}

    //Atualizar um produto existente
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setActive(productDetails.getActive());
        product.setCategory(productDetails.getCategory());

        return productRepository.save(product);
    }

    //Deletar um produto
    @DeleteMapping("/{id}") 
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}