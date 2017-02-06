package com.example.repositories;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dani on 2017-02-06.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
