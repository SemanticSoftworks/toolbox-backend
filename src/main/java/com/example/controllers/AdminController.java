package com.example.controllers;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.UserRole;
import com.example.model.AdminUserAdderDTO;
import com.example.model.AdminUserDTO;
import com.example.model.RoleDTO;
import com.example.service.RoleService;
import com.example.service.UserRoleService;
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

/**
 * Created by dani on 2017-02-10.
 */
@RestController
@RequestMapping("/admin")
public class AdminController{

    // hämta x
    // ta bort - avaktivera user
    // redigera i user
    // add roles
    // add category

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/user/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<AdminUserDTO>> getUsers(@RequestParam Long startPosition, @RequestParam Long endPosition){
        List<AdminUserDTO> userDTOList = new ArrayList<>();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> userList = userService.findAllUsers(startPosition,endPosition);

        for(User user : userList){
            AdminUserDTO userDTO = new AdminUserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstname(user.getFirstName());
            userDTO.setLastname(user.getLastName());
            userDTO.setUserRoles(extractUserRoles(user.getUserRole()));
            userDTO.setEnabled(user.isEnabled());

            userDTOList.add(userDTO);
        }

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @RequestMapping(value="/user/add", method = RequestMethod.POST, consumes={"application/json"})
    public ResponseEntity<AdminUserDTO> register(@RequestBody AdminUserAdderDTO incomingUser){
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AdminUserDTO userDTO = new AdminUserDTO();
        User userToAdd = userService.findByUsername(incomingUser.getUsername());

        if(userToAdd == null){
            User newUser = new User();
            newUser.setUsername(incomingUser.getUsername());
            newUser.setPassword(incomingUser.getPassword());
            newUser.setEmail(incomingUser.getEmail());
            newUser.setEnabled(incomingUser.isEnabled());
            newUser.setFirstName(incomingUser.getFirstname());
            newUser.setLastName(incomingUser.getLastname());

            User mockUser = userService.addUser(newUser);

            if(mockUser != null){
                for(String role : incomingUser.getUserRoles()){
                    Role realRole = roleService.getRole(role);
                    if(realRole != null) {

                        UserRole newUserRole = new UserRole();
                        newUserRole.setUser(mockUser);
                        newUserRole.setRole(realRole);
                        userRoleService.addRole(newUserRole);
                    }
                }
                userDTO.setId(mockUser.getId());
                userDTO.setUsername(mockUser.getUsername());
                userDTO.setEmail(mockUser.getEmail());
                userDTO.setFirstname(mockUser.getFirstName());
                userDTO.setLastname(mockUser.getLastName());
                userDTO.setUserRoles(extractUserRoles(mockUser.getUserRole()));
                userDTO.setEnabled(mockUser.isEnabled());
            }
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value="/user/accountActivation", method = RequestMethod.POST)
    public ResponseEntity<AdminUserDTO> accountActivation(@RequestParam Long id , @RequestParam Boolean enable){
        AdminUserDTO userToReturn = new AdminUserDTO();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findUserById(id);
        user.setEnabled(enable);
        user = userService.uppdateUser(user);

        userToReturn.setId(user.getId());
        userToReturn.setUserRoles(extractUserRoles(user.getUserRole()));
        userToReturn.setUsername(user.getUsername());
        userToReturn.setEmail(user.getEmail());
        userToReturn.setFirstname(user.getFirstName());
        userToReturn.setLastname(user.getLastName());
        userToReturn.setEnabled(user.isEnabled());

        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    @RequestMapping(value="/user/changePassword", method = RequestMethod.POST)
    public ResponseEntity<AdminUserDTO> changePassword(@RequestParam Long id , @RequestParam String newPassword){
        AdminUserDTO adminUserDTO= new AdminUserDTO();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findUserById(id);
        user.setPassword(newPassword);
        user = userService.uppdateUser(user);

        adminUserDTO.setId(user.getId());
        adminUserDTO.setUsername(user.getUsername());
        adminUserDTO.setEmail(user.getEmail());
        adminUserDTO.setFirstname(user.getFirstName());
        adminUserDTO.setUserRoles(extractUserRoles(user.getUserRole()));
        adminUserDTO.setLastname(user.getLastName());
        adminUserDTO.setEnabled(user.isEnabled());

        return new ResponseEntity<>(adminUserDTO, HttpStatus.OK);
    }

    // put?
    @RequestMapping(value="/role/add", method = RequestMethod.POST)
    public ResponseEntity<RoleDTO> addRole(@RequestParam String newRole){
        RoleDTO roleDTO = new RoleDTO();
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Role role = new Role();
        role.setRole("ROLE_"+newRole.toUpperCase());
        role = roleService.addRole(role);

        roleDTO.setRoleId(role.getRoleID());
        roleDTO.setRole(role.getRole());

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    private List<String> extractUserRoles(Set<UserRole> roles){
        List<String> rolesToAdd = new ArrayList<>();
        for (UserRole role : roles) {
            rolesToAdd.add(role.getRole().getRole());
        }
        return rolesToAdd;
    }
}
