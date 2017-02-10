package com.example.service.impl;

import com.example.domain.Category;
import com.example.repositories.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-10.
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category newCategory) {
        Category category = categoryRepository.findByName(newCategory.getName());
        if(category == null)
            return categoryRepository.save(newCategory);
        return null;
    }
}
