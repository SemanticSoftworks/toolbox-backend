package com.example.service.impl;

import com.example.domain.UserRole;
import com.example.repositories.UserRoleRepository;
import com.example.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-08.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole addRole(UserRole newUserRole) {
        return userRoleRepository.save(newUserRole);
    }
}
