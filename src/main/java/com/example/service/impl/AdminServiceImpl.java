package com.example.service.impl;

import com.example.domain.*;
import com.example.repositories.*;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dani on 2017-02-12.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Category addCategory(Category newCategory) {
        Category category = categoryRepository.findByName(newCategory.getName());
        if(category == null)
            return categoryRepository.save(newCategory);
        return null;
    }

    @Override
    public Category findCategoryById(Long id) { return categoryRepository.findOne(id); }

    @Override
    public Category updateCategory(Category category) { return categoryRepository.save(category); }

    @Override
    public List<Category> getCategories() { return categoryRepository.findAll(); }

    @Override
    public UserRole addUserRole(UserRole newUserRole) { return userRoleRepository.save(newUserRole); }

    @Override
    public List<Role> getRoles() { return roleRepository.findAll(); }

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
    public Role findRoleById(Integer id) { return roleRepository.findOne(id); }

    @Override
    public Role updateRole(Role updateRole) { return roleRepository.save(updateRole); }

    @Override
    public Transaction deleteTransactionById(Long id) {
        Transaction transaction = transactionRepository.findByTransactionId(id);

        if(transaction != null){
            transactionRepository.delete(transaction);
        }
        return transaction;
    }

    @Override
    public Ad deleteAdById(long id) {
        Ad ad = adRepository.findByAdId(id);

        if(ad != null)
            adRepository.delete(ad);

        return ad;
    }

    @Override
    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public User addUser(User newUser) {
        User savedUser = null;

        User userCheck = userRepository.findByUsername(newUser.getUsername());
        if(userCheck == null)
            savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        if(user != null){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User findUserById(Long id) { return userRepository.findById(id); }

    @Override
    public List<User> findAllUsers(Long startPosition, Long endPosition) { return userCustomRepository.getUsers(startPosition,endPosition); }

    @Override
    public Transaction findByTransactionId(Long id) { return transactionRepository.findByTransactionId(id); }

    @Override
    public Transaction addTransaction(Transaction newTransaction) {
        if(newTransaction != null)
            return transactionRepository.save(newTransaction);

        return null;
    }


}
