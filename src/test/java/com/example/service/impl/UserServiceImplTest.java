package com.example.service.impl;

import com.example.domain.User;
import com.example.repositories.UserRepository;
import com.example.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by dani on 2017-02-15.
 */
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Before
    public void setUp(){ MockitoAnnotations.initMocks(this); }

    @Test
    public void findByUserNameAndPassword() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setUsername("anders");

        user.setPassword("$2a$06$CDyAlnH2sR4taKM/ZoRIguvnY6/5rKHS.uDpJGvI8B.LQqD.OWQ5a"); // base64 hashning
        user.setEmail("anders@kth.se");
        user.setEnabled(true);
        user.setFirstName("Anders");
        user.setLastName("Andersson");

        when(userRepository.findByUsername("anders")).thenReturn(user);
        User userCheck = userService.findByUserNameAndPassword("anders", "123123");

        Assert.assertEquals(userCheck , user);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("anders");
        user.setId(1L);
        user.setPassword("$2a$06$CDyAlnH2sR4taKM/ZoRIguvnY6/5rKHS.uDpJGvI8B.LQqD.OWQ5a");

        when(userRepository.findByUsername("anders")).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        User userReturned = userService.addUser(user);
        Assert.assertEquals(user, userReturned);
    }
}