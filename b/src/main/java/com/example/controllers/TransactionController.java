package com.example.controllers;

import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.IncomingTransactionDTO;
import com.example.model.TransactionDetailDTO;
import com.example.model.UserDTO;
import com.example.service.TransactionService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by dani on 2017-02-09.
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionDetailDTO> getTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.findByTransactionId(id);
        TransactionDetailDTO transactionDTO = new TransactionDetailDTO();

        if(transaction != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(transaction.getUser().getId());
            userDTO.setUsername(transaction.getUser().getUsername());
            userDTO.setEmail(transaction.getUser().getEmail());
            userDTO.setFirstname(transaction.getUser().getFirstName());
            userDTO.setLastname(transaction.getUser().getLastName());
            userDTO.setUserRoles(extractUserRoles(transaction.getUser().getUserRole()));

            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setUser(userDTO);
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setSum(transaction.getSum());
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate() : null);
        }

        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<TransactionDetailDTO> addTransaction(@RequestBody IncomingTransactionDTO incomingTransaction){
        TransactionDetailDTO transactionDTO = new TransactionDetailDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Transaction newTransaction = new Transaction();

        logger.info("userDetails: "+userDetails.getUsername() + " incoming: "+incomingTransaction.getDate() + " "+incomingTransaction.getDescription() + " "+incomingTransaction.getSum());

        newTransaction.setUser(user);
        newTransaction.setDate(Calendar.getInstance());
        newTransaction.setDescription(incomingTransaction.getDescription());
        newTransaction.setSum(incomingTransaction.getSum());
        Transaction mockTransaction = transactionService.addTransaction(newTransaction);

        if(mockTransaction != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstname(user.getFirstName());
            userDTO.setLastname(user.getLastName());
            userDTO.setUserRoles(extractUserRoles(user.getUserRole()));

            transactionDTO.setTransactionId(mockTransaction.getTransactionId());
            transactionDTO.setDescription(mockTransaction.getDescription());
            transactionDTO.setSum(mockTransaction.getSum());
            transactionDTO.setUser(userDTO);
            transactionDTO.setDate(mockTransaction.getDate() != null ? mockTransaction.getDate() : null);
        }

        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    private List<String> extractUserRoles(Set<UserRole> roles){
        List<String> rolesToAdd = new ArrayList<>();
        for (UserRole role : roles) {
            rolesToAdd.add(role.getRole().getRole());
        }
        return rolesToAdd;
    }
}
