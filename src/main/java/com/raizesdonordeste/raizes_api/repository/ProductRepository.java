package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByCategoryId(Long categoryId);

}
