package com.raizesdonordeste.raizes_api.repository;

import com.raizesdonordeste.raizes_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
