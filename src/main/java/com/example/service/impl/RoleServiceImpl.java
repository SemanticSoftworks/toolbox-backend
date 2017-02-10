package com.example.service.impl;

import com.example.domain.Role;
import com.example.repositories.RoleRepository;
import com.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dani on 2017-02-08.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role newRole) {
        Role role = roleRepository.findByRole(newRole.getRole());
        if(role == null)
            return roleRepository.save(newRole);
        return null;
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findOne(id);
    }
}
