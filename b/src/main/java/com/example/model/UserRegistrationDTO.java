package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dani on 2017-02-08.
 */
public class UserRegistrationDTO {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    @JsonCreator
    public UserRegistrationDTO(@JsonProperty("username")String username,@JsonProperty("password") String password,@JsonProperty("email") String email,@JsonProperty("firstname") String firstname,@JsonProperty("lastname") String lastname){
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstname=firstname;
        this.lastname=lastname;
    }

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
}
