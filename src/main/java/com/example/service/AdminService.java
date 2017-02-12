package com.example.service;

import com.example.domain.Category;
import com.example.domain.Role;

/**
 * Created by dani on 2017-02-12.
 */
public interface AdminService {
    Category addCategory(Category newCategory);
    Role addRole(Role newRole);
    Role getRole(String role);
    Role findById(Integer id);
}
