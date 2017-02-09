package com.example.repositories;

import com.example.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dani on 2017-02-08.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
