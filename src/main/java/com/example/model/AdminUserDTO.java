package com.example.model;

import java.util.List;

/**
 * Created by dani on 2017-02-10.
 */
public class AdminUserDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> userRoles;
    private boolean enabled;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<String> getUserRoles() { return userRoles; }

    public void setUserRoles(List<String> userRoles) { this.userRoles = userRoles; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
