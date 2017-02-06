package com.example.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dani on 16/06/16.
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleID;

    @Column(name = "role", length = 45)
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Integer getRoleID() {
        return this.roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Set<UserRole> getUserRoleId() {
        return userRole;
    }

    public void setUserRoleId(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}