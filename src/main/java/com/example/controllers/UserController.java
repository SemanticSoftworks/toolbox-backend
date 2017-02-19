package com.example.controllers;

import com.example.Hash;
import com.example.domain.Ad;
import com.example.domain.Transaction;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.*;
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
            userDTO.setAds(extractAds(user.getAds()));
            userDTO.setTransactions(extractTransactions(user.getTransactions()));
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // base 64 encoding
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<UserDTO> login(@RequestBody UserAuthenticationDTO incomingUser){
        logger.info("THIS says: username: "+incomingUser.getUsername() + " password: "+incomingUser.getPassword());
        User tmpUser = userService.findByUserNameAndPassword(incomingUser.getUsername(), incomingUser.getPassword());
        UserDTO userToReturn = new UserDTO();

        if(tmpUser != null && tmpUser.isEnabled()){
            userToReturn.setId(tmpUser.getId());
            userToReturn.setUsername(tmpUser.getUsername());
            userToReturn.setEmail(tmpUser.getEmail());
            userToReturn.setFirstname(tmpUser.getFirstName());
            userToReturn.setLastname(tmpUser.getLastName());

            userToReturn.setUserRoles(extractUserRoles(tmpUser.getUserRole()));
            return new ResponseEntity<>(userToReturn, HttpStatus.OK);
        }

        return new ResponseEntity<>(userToReturn, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO incomingUser){
        logger.info("username of incoming user: "+incomingUser.getUsername());
        User tmpUser = userService.findByUserNameAndPassword(incomingUser.getUsername(), incomingUser.getPassword());
        UserDTO userDTO = new UserDTO();

        if(tmpUser == null){
            User newUser = new User();
            newUser.setUsername(incomingUser.getUsername());
            newUser.setPassword(Hash.BcryptEncrypt(incomingUser.getPassword()));
            newUser.setEmail(incomingUser.getEmail());
            newUser.setEnabled(true);
            newUser.setFirstName(incomingUser.getFirstname());
            newUser.setLastName(incomingUser.getLastname());

            User mockUser = userService.addUser(newUser);

            if(mockUser != null){
                UserRole newUserRole = new UserRole();
                newUserRole.setUser(mockUser);
                newUserRole.setRole(userService.getRole("ROLE_AUCTIONEER"));
                userService.addUserRole(newUserRole);

                userDTO.setId(mockUser.getId());
                userDTO.setUsername(mockUser.getUsername());
                userDTO.setEmail(mockUser.getEmail());
                userDTO.setFirstname(mockUser.getFirstName());
                userDTO.setLastname(mockUser.getLastName());
                userDTO.setUserRoles(extractUserRoles(mockUser.getUserRole()));
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
    }

    // cant change username & disable!
    @RequestMapping(value="/update" , method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRegistrationDTO incomingUser){
        UserDTO userDTO = new UserDTO();

        User userToUpdate = userService.findByUsername(incomingUser.getUsername());
        userToUpdate.setPassword(incomingUser.getPassword());
        userToUpdate.setFirstName(incomingUser.getFirstname());
        userToUpdate.setLastName(incomingUser.getLastname());
        userToUpdate.setEmail(incomingUser.getEmail());

        User userReturned = userService.updateUser(userToUpdate);
        if(userReturned != null){
            userDTO.setId(userReturned.getId());
            userDTO.setUsername(userReturned.getUsername());
            userDTO.setEmail(userReturned.getEmail());
            userDTO.setUserRoles(extractUserRoles(userReturned.getUserRole()));
            userDTO.setFirstname(userReturned.getFirstName());
            userDTO.setLastname(userReturned.getLastName());

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/user/forgotpassword", method = RequestMethod.POST)
    public ResponseEntity< UserUpdateDTO> changePassword(@RequestParam Long id , @RequestParam String newPassword){
        UserUpdateDTO adminUserDTO= new UserUpdateDTO();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findUserById(id);
        user.setPassword(newPassword);
        user = userService.updateUser(user);

        if(user != null) {
            adminUserDTO.setId(user.getId());
            adminUserDTO.setUsername(user.getUsername());
            adminUserDTO.setEmail(user.getEmail());
            adminUserDTO.setFirstname(user.getFirstName());
            adminUserDTO.setUserRoles(extractUserRoles(user.getUserRole()));
            adminUserDTO.setLastname(user.getLastName());
            adminUserDTO.setPassword(user.getPassword());

            return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(adminUserDTO, HttpStatus.BAD_REQUEST);
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

            transactionDTO.setDescription(mockTransaction.getDescription());
            transactionDTO.setSum(mockTransaction.getSum());
            transactionDTO.setTransactionId(mockTransaction.getTransactionId());
            transactionDTO.setDate(mockTransaction.getDate() != null ? mockTransaction.getDate().getTime().toString() : null);

            transactionDTOList.add(transactionDTO);
        }
        return transactionDTOList;
    }

    private List<AdDTO> extractAds(List<Ad> realAds){
        List<AdDTO> adDTOList = new ArrayList<>();
        for (Ad mockAd : realAds) {
            if(mockAd != null) {
                AdDTO adDTO = new AdDTO(mockAd.getAdId(), mockAd.getUser().getId(), new CategoryDTO(mockAd.getCategory() != null ? mockAd.getCategory().getCategoryId() : null, mockAd.getCategory() != null ? mockAd.getCategory().getName() : null), mockAd.getTitle(), mockAd.getDescription(), mockAd.getDuration() != null ? mockAd.getDuration() : null);
                adDTOList.add(adDTO);
            }
        }
        return adDTOList;
    }
}
