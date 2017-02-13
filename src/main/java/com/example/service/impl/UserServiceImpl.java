package com.example.service.impl;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserCustomRepository;
import com.example.repositories.UserRepository;
import com.example.repositories.UserRoleRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dani on 2017-02-06.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        User tmpUser = userRepository.findByUsername(username);

        if(tmpUser != null){
            boolean correctPassword = BCrypt.checkpw(password, tmpUser.getPassword()); // om skickad hashad lösenord = databasens
            if(correctPassword){
                return tmpUser;
            }
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(User newUser) {
        User savedUser = null;

        User userCheck = userRepository.findByUsername(newUser.getUsername());
        if(userCheck == null)
            savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User uppdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers(Long startPosition, Long endPosition) { return userCustomRepository.getUsers(startPosition,endPosition); }

    @Override
    public Role getRole(String role) { return roleRepository.findByRole(role); }

    @Override
    public UserRole addUserRole(UserRole newUserRole) { return userRoleRepository.save(newUserRole); }

    @Override
    public User updateUser(User user) {
        
        if(user != null){
            return userRepository.save(user);
        }
        return null;
    }
}
