package com.example.service.impl;

import com.example.domain.Category;
import com.example.domain.Role;
import com.example.repositories.CategoryRepository;
import com.example.repositories.RoleRepository;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-12.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Category addCategory(Category newCategory) {
        Category category = categoryRepository.findByName(newCategory.getName());
        if(category == null)
            return categoryRepository.save(newCategory);
        return null;
    }

    @Override
    public Role addRole(Role newRole) {
        Role role = roleRepository.findByRole(newRole.getRole());
        if(role == null)
            return roleRepository.save(newRole);
        return null;
    }

    @Override
    public Role getRole(String role) { return roleRepository.findByRole(role); }

    @Override
    public Role findById(Integer id) { return roleRepository.findOne(id); }
}
