package com.example.controllers;

import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.TransactionDTO;
import com.example.model.UserAuthenticationDTO;
import com.example.model.UserDTO;
import com.example.model.UserDetailDTO;
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
    public ResponseEntity<UserDetailDTO> getUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        UserDetailDTO userDTO = new UserDetailDTO();
        logger.info("ID is: "+id);
        if(user != null){
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setLastname(user.getLastName());
            userDTO.setFirstname(user.getFirstName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUserRoles(extractUserRoles(user.getUserRole()));
            userDTO.setTransactions(extractTransactions(user.getTransactions()));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
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

            userToReturn.setUserRoles(extractUserRoles(tmpUser.getUserRole()));
        }

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    private List<String> extractUserRoles(Set<UserRole> roles){
        List<String> rolesToAdd = new ArrayList<>();
        for (UserRole role : roles) {
            rolesToAdd.add(role.getRole().getRole());
        }
        return rolesToAdd;
    }

    private List<TransactionDTO> extractTransactions(List<Transaction> realTransactions){
        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        for(Transaction mockTransaction : realTransactions){
            TransactionDTO transactionDTO = new TransactionDTO();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(mockTransaction.getUser().getId());
            userDTO.setFirstname(mockTransaction.getUser().getFirstName());
            userDTO.setLastname(mockTransaction.getUser().getLastName());
            userDTO.setEmail(mockTransaction.getUser().getEmail());
            userDTO.setUserRoles(extractUserRoles(mockTransaction.getUser().getUserRole()));

            transactionDTO.setUser(userDTO);
            transactionDTO.setDescription(mockTransaction.getDescription());
            transactionDTO.setSum(mockTransaction.getSum());
            transactionDTO.setTransactionId(mockTransaction.getTransactionId());
            transactionDTO.setDate(mockTransaction.getDate() != null ? mockTransaction.getDate().toString() : null);

            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }
}
