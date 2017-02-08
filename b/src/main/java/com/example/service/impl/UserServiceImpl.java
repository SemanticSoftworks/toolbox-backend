package com.example.service.impl;

import com.example.domain.User;
import com.example.repositories.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-06.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
}
