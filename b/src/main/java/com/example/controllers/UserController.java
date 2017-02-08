package com.example.controllers;

import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.UserAuthenticationDTO;
import com.example.model.UserDTO;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id){
        User user = userService.findUserById(id);

        if(user == null){
            return "user is null";
        }

        return "user id and name: "+id+ " , "+user.getUsername();
    }

    // base 64 encoding
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<UserDTO> login(@RequestBody UserAuthenticationDTO incomingUser){
        User tmpUser = userService.findByUserNameAndPassword(incomingUser.getUsername(), incomingUser.getPassword());
        logger.info("THIS says: username: "+incomingUser.getUsername() + " password: "+incomingUser.getPassword());
        UserDTO userToReturn = new UserDTO();

        if(tmpUser != null && tmpUser.isEnabled()){
            userToReturn.setId(tmpUser.getId());
            userToReturn.setUsername(tmpUser.getUsername());
            userToReturn.setEmail(tmpUser.getEmail());
            userToReturn.setFirstname(tmpUser.getFirstName());
            userToReturn.setLastname(tmpUser.getLastName());

            Set<UserRole> roles = tmpUser.getUserRole();
            List<String> rolesToAdd = new ArrayList<>();
            for (UserRole role : roles) {
                rolesToAdd.add(role.getRole().getRole());
            }

            userToReturn.setUserRoles(rolesToAdd);
        }

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }
}
