package com.raizesdonordeste.raizes_api.controller;

import com.raizesdonordeste.raizes_api.entity.Category;
import com.raizesdonordeste.raizes_api.repository.ProductRepository;
import com.raizesdonordeste.raizes_api.repository.CategoryRepository;
import com.raizesdonordeste.raizes_api.exception.ResourceNotFoundException;

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
    //Buscar uma categoria por ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    //Inserir uma nova categoria
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    //Atualizar uma categoria existente
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());

        return categoryRepository.save(category);
    }

    //Deletar uma categoria
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {

        if (productRepository.existsByCategoryId(id)) {
            throw new ResourceNotFoundException("Not possible to delete category with associated products");
        }
        categoryRepository.deleteById(id);
    }

}
