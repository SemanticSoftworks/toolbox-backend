package com.example.model;

import java.util.List;

/**
 * Created by dani on 2017-02-13.
 */
public class UserUpdateDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private List<String> userRoles;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public List<String> getUserRoles() { return userRoles; }

    public void setUserRoles(List<String> userRoles) { this.userRoles = userRoles; }
}
