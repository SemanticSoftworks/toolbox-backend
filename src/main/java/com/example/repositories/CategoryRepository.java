package com.example.repositories;

import com.example.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dani on 2017-02-10.
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);
}
