package com.example.service;

import com.example.domain.Role;

/**
 * Created by dani on 2017-02-08.
 */
public interface RoleService {
    Role addRole(Role newRole);
    Role getRole(String role);
}
