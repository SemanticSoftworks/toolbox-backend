package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by dani on 2017-02-10.
 */
public class AdminUserDTO {

    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> userRoles;
    private boolean enabled;

    @JsonCreator
    public AdminUserDTO(@JsonProperty("id") Long id, @JsonProperty("username") String username,@JsonProperty("password") String password,@JsonProperty("firstname") String firstname,@JsonProperty("lastname") String lastname ,@JsonProperty("email") String email,@JsonProperty("userRoles") List<String> userRoles,@JsonProperty("enabled") boolean enabled){
        this.id=id;
        this.username=username;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.userRoles=userRoles;
        this.enabled=enabled;
    }

    public AdminUserDTO(){}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

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
