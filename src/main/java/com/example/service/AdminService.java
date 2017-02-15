package com.example.service;

import com.example.domain.*;

import java.util.List;

/**
 * Created by dani on 2017-02-12.
 */
public interface AdminService {
    Category addCategory(Category newCategory);
    Category findCategoryById(Long id);
    Category updateCategory(Category category);
    Role addRole(Role newRole);
    Role getRole(String role);
    Role findRoleById(Integer id);
    Role updateRole(Role updateRole);
    Transaction deleteTransactionById(Long id);
    Ad deleteAdById(long id);
    List<User> findAllUsers(Long startPosition, Long endPosition);
}
