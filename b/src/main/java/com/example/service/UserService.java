package com.example.service;

import com.example.domain.User;

/**
 * Created by dani on 2017-02-06.
 */

public interface UserService {

    User findUserById(Long id);
}
