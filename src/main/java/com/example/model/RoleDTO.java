package com.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by dani on 2017-02-10.
 */
public class RoleDTO {

    private Integer roleId;
    private String role;

    @JsonCreator
    public RoleDTO(@JsonProperty("roleId") Integer roleId,@JsonProperty("role") String role){
        this.roleId=roleId;
        this.role=role;
    }

    public RoleDTO(){}

    public Integer getRoleId() { return roleId; }

    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
