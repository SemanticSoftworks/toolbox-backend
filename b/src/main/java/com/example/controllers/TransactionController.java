package com.example.controllers;

import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.TransactionDTO;
import com.example.model.UserDTO;
import com.example.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by alica on 2017-02-08.
 * Good luck, Commander!
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public String getTransaction(@PathVariable Long transactionId){
        Transaction transaction = transactionService.findTransactionById(transactionId);
        if(transaction == null){
            return "transaction is null";
        }
        return "transaction id and user id: " + transactionId + " , " + transaction.getUser().getId();
    }//getTransaction

    @RequestMapping(value = "/setTransaction", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Transaction> setTransaction(@RequestBody TransactionDTO incomingTransaction){
        Transaction tmpTransaction = new Transaction();
        logger.info("THIS says: transaction Id: " + incomingTransaction.getTransactionId());

        User user = new User();
        user.setId(tmpTransaction.getUser().getId());
        user.setFirstName(tmpTransaction.getUser().getFirstName());
        user.setLastName(tmpTransaction.getUser().getLastName());
        user.setUsername(tmpTransaction.getUser().getUsername());
        user.setEmail(tmpTransaction.getUser().getEmail());

        tmpTransaction.setTransactionId(incomingTransaction.getTransactionId());
        tmpTransaction.setUser(user);
        tmpTransaction.setDate(incomingTransaction.getDate());
        tmpTransaction.setDescription(incomingTransaction.getDescription());
        tmpTransaction.setSum(incomingTransaction.getSum());

        int status = transactionService.addTransaction(tmpTransaction);
        if(status == 1){
            return new ResponseEntity<>(tmpTransaction, HttpStatus.OK);
        } else
            return null;
    }//setTransaction

    @RequestMapping(value = "/getTransactionById", method = RequestMethod.GET, consumes = {"application/json"})
    public ResponseEntity<TransactionDTO> getTransactionById(@RequestBody Long transactionId){
        Transaction tmpTransaction = transactionService.findTransactionById(transactionId);
        logger.info("THIS says: transaction id: " + transactionId);
        TransactionDTO transactionToReturn = new TransactionDTO();

        if(tmpTransaction != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(tmpTransaction.getUser().getId());
            userDTO.setFirstname(tmpTransaction.getUser().getFirstName());
            userDTO.setLastname(tmpTransaction.getUser().getLastName());
            userDTO.setUsername(tmpTransaction.getUser().getUsername());
            userDTO.setEmail(tmpTransaction.getUser().getEmail());

            Set<UserRole> roles = tmpTransaction.getUser().getUserRole();
            List<String> rolesToAdd = new ArrayList<>();
            for (UserRole role : roles) {
                rolesToAdd.add(role.getRole().getRole());
            }//for
            userDTO.setUserRoles(rolesToAdd);

            transactionToReturn.setTransactionId(tmpTransaction.getTransactionId());
            transactionToReturn.setUser(userDTO);
            transactionToReturn.setDate(tmpTransaction.getDate());
            transactionToReturn.setDescription(tmpTransaction.getDescription());
            transactionToReturn.setSum(tmpTransaction.getSum());
        }//if

        return new ResponseEntity<>(transactionToReturn, HttpStatus.OK);
    }//getTransactionById

    @RequestMapping(value = "/getTransactionByUserId", method = RequestMethod.GET, consumes = {"application/json"})
    public ResponseEntity<List<TransactionDTO>> getTransactionByUserId(@RequestBody Long userId){
        List<Transaction> tmpTransaction = transactionService.findTransactionByUserId(userId);
        logger.info("THIS says: userId: " + userId);
        List<TransactionDTO> transactionToReturn = new ArrayList<>();
        TransactionDTO transactionDTO = new TransactionDTO();

        if(tmpTransaction != null){
            for(int i = 0; i < tmpTransaction.size(); i++){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(tmpTransaction.get(i).getUser().getId());
                userDTO.setFirstname(tmpTransaction.get(i).getUser().getFirstName());
                userDTO.setLastname(tmpTransaction.get(i).getUser().getLastName());
                userDTO.setUsername(tmpTransaction.get(i).getUser().getUsername());
                userDTO.setEmail(tmpTransaction.get(i).getUser().getEmail());

                Set<UserRole> roles = tmpTransaction.get(i).getUser().getUserRole();
                List<String> rolesToAdd = new ArrayList<>();
                for (UserRole role : roles) {
                    rolesToAdd.add(role.getRole().getRole());
                }//for
                userDTO.setUserRoles(rolesToAdd);

                transactionDTO.setTransactionId(tmpTransaction.get(i).getTransactionId());
                transactionDTO.setUser(userDTO);
                transactionDTO.setDate(tmpTransaction.get(i).getDate());
                transactionDTO.setDescription(tmpTransaction.get(i).getDescription());
                transactionDTO.setSum(tmpTransaction.get(i).getSum());

                transactionToReturn.add(transactionDTO);
            }//for
        }//if

        return new ResponseEntity<>(transactionToReturn, HttpStatus.OK);
    }//getTransactionByUserId
}//class

