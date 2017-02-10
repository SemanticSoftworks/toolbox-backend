package com.example.repositories;

import com.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dani on 2017-02-10.
 */
@Repository
public interface UserCustomRepository{
    List<User> getUsers(Long start, Long end);
}
