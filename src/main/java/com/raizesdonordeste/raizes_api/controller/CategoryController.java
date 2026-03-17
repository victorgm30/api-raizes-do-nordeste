package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Category;
import com.raizesdonordeste.raizes_api.repository.ProductRepository;
import com.raizesdonordeste.raizes_api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Define a classe como um controlador REST
@RequestMapping("/categories") //Cria um endpoint para acessar as categorias
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    //Listar todas as categorias
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    //Inserir uma nova categoria
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    //Deletar uma categoria
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {

        if (productRepository.existsByCategoryId(id)) {
            throw new RuntimeException("Não é possível deletar a categoria, existem produtos associados a ela.");
        }
        categoryRepository.deleteById(id);
    }

}
