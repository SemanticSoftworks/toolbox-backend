package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserRole;

import java.util.List;

/**
 * Created by dani on 2017-02-06.
 */

public interface UserService {
    User findByUserNameAndPassword(String username, String password);
    User findUserById(Long id);
    User findByUsername(String username);
    User addUser(User newUser);
    User uppdateUser(User user);
    List<User> findAllUsers(Long startPosition, Long endPosition);
    Role getRole(String role);
    UserRole addUserRole(UserRole newUserRole);
}
