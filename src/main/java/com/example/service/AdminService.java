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
    List<Category> getCategories();
    UserRole addUserRole(UserRole newUserRole);
    List<Role> getRoles();
    Role addRole(Role newRole);
    Role getRole(String role);
    Role findRoleById(Integer id);
    Role updateRole(Role updateRole);
    Transaction deleteTransactionById(Long id);
    Ad deleteAdById(long id);
    User findByUsername(String username);
    User addUser(User newUser);
    User updateUser(User user);
    User findUserById(Long id);
    List<User> findAllUsers(Long startPosition, Long endPosition);
    Transaction findByTransactionId(Long id);
    Transaction addTransaction(Transaction newTransaction);
}
