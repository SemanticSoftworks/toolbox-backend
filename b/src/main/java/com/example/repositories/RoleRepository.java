package com.example.repositories;

import com.example.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dani on 2017-02-08.
 */
@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
