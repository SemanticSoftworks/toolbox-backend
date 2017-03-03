package com.example.controllers;

import com.example.Hash;
import com.example.domain.*;
import com.example.model.*;
import com.example.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dani on 2017-02-10.
 */
@RestController
@RequestMapping("/admin")
public class AdminController{

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    //list categories + roles

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<AdminUserDTO>> getUsers(@RequestParam Long startPosition, @RequestParam Long endPosition){
        List<AdminUserDTO> userDTOList = new ArrayList<>();
    //    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> userList = adminService.findAllUsers(startPosition,endPosition);

        for(User user : userList){
            AdminUserDTO userDTO = new AdminUserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstname(user.getFirstName());
            userDTO.setLastname(user.getLastName());
            userDTO.setUserRoles(extractUserRoles(user.getUserRole()));
            userDTO.setEnabled(user.isEnabled());
            userDTO.setPassword(user.getPassword());

            userDTOList.add(userDTO);
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    // admin kan adda user
    @RequestMapping(value="/user", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<AdminUserDTO> register(@RequestBody AdminUserAdderDTO incomingUser){
        AdminUserDTO userDTO = new AdminUserDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToAdd = adminService.findByUsername(incomingUser.getUsername());

        if(userToAdd == null){
            User newUser = new User();
            newUser.setUsername(incomingUser.getUsername());
            newUser.setPassword(Hash.BcryptEncrypt(incomingUser.getPassword()));
            newUser.setEmail(incomingUser.getEmail());
            newUser.setEnabled(incomingUser.isEnabled());
            newUser.setFirstName(incomingUser.getFirstname());
            newUser.setLastName(incomingUser.getLastname());

            User mockUser = adminService.addUser(newUser);

            if(mockUser != null){
                for(String role : incomingUser.getUserRoles()){
                    Role realRole = adminService.getRole(role);
                    if(realRole != null) {

                        UserRole newUserRole = new UserRole();
                        newUserRole.setUser(mockUser);
                        newUserRole.setRole(realRole);
                        adminService.addUserRole(newUserRole);
                    }
                }
                userDTO.setId(mockUser.getId());
                userDTO.setUsername(mockUser.getUsername());
                userDTO.setEmail(mockUser.getEmail());
                userDTO.setFirstname(mockUser.getFirstName());
                userDTO.setLastname(mockUser.getLastName());
                userDTO.setUserRoles(extractUserRoles(mockUser.getUserRole()));
                userDTO.setEnabled(mockUser.isEnabled());
                userDTO.setPassword(mockUser.getPassword());
            }
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // avaktivera - aktivera en user
    @RequestMapping(value="/user/accountActivation/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdminUserDTO> accountActivation(@PathVariable Long id , @RequestParam Boolean enable){
        AdminUserDTO userToReturn = new AdminUserDTO();
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = adminService.findUserById(id);
        user.setEnabled(enable);
        user = adminService.updateUser(user);

        userToReturn.setId(user.getId());
        userToReturn.setUserRoles(extractUserRoles(user.getUserRole()));
        userToReturn.setUsername(user.getUsername());
        userToReturn.setEmail(user.getEmail());
        userToReturn.setFirstname(user.getFirstName());
        userToReturn.setLastname(user.getLastName());
        userToReturn.setEnabled(user.isEnabled());

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    private Set<UserRole> userRoles(List<String> fakeRoles, User user){
        Set<UserRole> userRoles = new HashSet<>();

        // delete existing userRoles
        for(UserRole roleToDelete : user.getUserRole()){
            adminService.deleteUserRole(roleToDelete);
        }

        // add ones not there
        for(String userRole : fakeRoles){
            UserRole realUserRole = new UserRole();
            realUserRole.setUser(user);

            Role roleCheck = adminService.getRole(userRole);
            if(roleCheck != null){
                realUserRole.setRole(roleCheck);
                realUserRole = adminService.addUserRole(realUserRole);
                userRoles.add(realUserRole);
            }
        }

        return userRoles;
    }

    @RequestMapping(value="/user/update", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<AdminUserDTO> updateUser(@RequestBody AdminUserDTO incomingUser){

        AdminUserDTO adminUserDTO= new AdminUserDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = adminService.findUserById(incomingUser.getId());
        user.setPassword(Hash.BcryptEncrypt(incomingUser.getPassword()));
        user.setEmail(incomingUser.getEmail());
        user.setFirstName(incomingUser.getFirstname());
        user.setLastName(incomingUser.getLastname());
        user.setEnabled(incomingUser.isEnabled());
        user.setUserRole(userRoles(incomingUser.getUserRoles(),user));
        user = adminService.updateUser(user);

        if(user != null) {
            adminUserDTO.setId(user.getId());
            adminUserDTO.setUsername(user.getUsername());
            adminUserDTO.setPassword(user.getPassword());
            adminUserDTO.setEmail(user.getEmail());
            adminUserDTO.setFirstname(user.getFirstName());
            adminUserDTO.setUserRoles(extractUserRoles(user.getUserRole()));
            adminUserDTO.setLastname(user.getLastName());
            adminUserDTO.setEnabled(user.isEnabled());
            return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(adminUserDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/transaction" , method = RequestMethod.POST)
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestParam TransactionDTO incomingTransaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Transaction transaction = adminService.findByTransactionId(incomingTransaction.getTransactionId());
        transaction.setSum(incomingTransaction.getSum());
        transaction.setDescription(incomingTransaction.getDescription());
        transaction.setDate(Calendar.getInstance());

        transaction = adminService.addTransaction(transaction);
        if(transaction != null){
            transactionDTO.setDate(transaction.getDate().getTime().toString());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setSum(transaction.getSum());

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/transaction/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<TransactionDTO> deleteTransaction(@PathVariable Long id){
        TransactionDTO transactionDTO = new TransactionDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Transaction transaction = adminService.deleteTransactionById(id);
        if(transaction != null){
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setSum(transaction.getSum());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setDate(transaction.getDate() != null ? transaction.getDate().getTime().toString() : null);

            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(transactionDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/ad/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AdDTO> deleteAd(@PathVariable long id){
        AdDTO adDTO = null;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Ad ad = adminService.deleteAdById(id);

        if(ad != null){
            adDTO = new AdDTO(ad.getAdId(), ad.getUser().getId(), toCategoryDTO(ad.getCategory()).getName(), ad.getTitle(), ad.getDescription(), ad.getDuration());
            return new ResponseEntity<>(adDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(adDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/role", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> getRoles(){
        List<RoleDTO> roleListDTO = new ArrayList<>();

        List<Role> roleList = adminService.getRoles();
        for(Role role : roleList){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(role.getRoleID());
            roleDTO.setRole(role.getRole());

            roleListDTO.add(roleDTO);
        }

        return new ResponseEntity<>(roleListDTO, HttpStatus.OK);
    }

    // put?
    @RequestMapping(value="/role/add", method = RequestMethod.POST)
    public ResponseEntity<RoleDTO> addRole(@RequestParam String role){
        RoleDTO roleDTO = new RoleDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Role newRole = new Role();
        newRole.setRole("ROLE_"+role.toUpperCase());
        newRole = adminService.addRole(newRole);

        if(newRole != null) {
            roleDTO.setRoleId(newRole.getRoleID());
            roleDTO.setRole(newRole.getRole());
            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(roleDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/role/update", method = RequestMethod.POST)
    public ResponseEntity<RoleDTO> updateRole(@RequestParam RoleDTO incomingRole){
        RoleDTO roleDTO = new RoleDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Role role = adminService.findRoleById(incomingRole.getRoleId());
        role.setRole(incomingRole.getRole());
        role = adminService.updateRole(role);

        if(role != null){
            roleDTO.setRoleId(role.getRoleID());
            roleDTO.setRole(role.getRole());
            return new ResponseEntity<>(roleDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(roleDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/category", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        List<Category> categories = adminService.getCategories();
        for(Category category : categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getCategoryId());
            categoryDTO.setName(category.getName());

            categoryDTOList.add(categoryDTO);
        }

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @RequestMapping(value="/category", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> addCategory(@RequestParam String category){
        CategoryDTO categoryDTO = new CategoryDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category newCategory = new Category();
        newCategory.setName(category);

        Category categoryReturned = adminService.addCategory(newCategory);

        if(categoryReturned != null){
            categoryDTO.setCategoryId(categoryReturned.getCategoryId());
            categoryDTO.setName(categoryReturned.getName());
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(categoryDTO, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/category/update", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> updateCategory(@RequestParam CategoryDTO incomingCategory){
        CategoryDTO categoryDTO = new CategoryDTO();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = adminService.findCategoryById(incomingCategory.getCategoryId());
        category.setName(incomingCategory.getName());
        category = adminService.updateCategory(category);

        if(category != null){
            categoryDTO.setName(category.getName());
            categoryDTO.setCategoryId(category.getCategoryId());
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(categoryDTO, HttpStatus.BAD_REQUEST);
    }

    private List<String> extractUserRoles(Set<UserRole> roles){
        List<String> rolesToAdd = new ArrayList<>();
        for (UserRole role : roles) {
            rolesToAdd.add(role.getRole().getRole());
        }
        return rolesToAdd;
    }

    private CategoryDTO toCategoryDTO(Category category)
    {
        if(category == null)
        {
            return null;
        }
        return new CategoryDTO(category.getCategoryId(), category.getName());
    }
}
