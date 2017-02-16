package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserRole;

/**
 * Created by dani on 2017-02-06.
 */

public interface UserService {
    User findByUserNameAndPassword(String username, String password);
    User findUserById(Long id);
    User findByUsername(String username);
    User addUser(User newUser);
    Role getRole(String role);
    UserRole addUserRole(UserRole newUserRole);
    User updateUser(User user);
}
