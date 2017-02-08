package com.example.domain;

import javax.persistence.*;

/**
 * Created by dani on 15/06/16.
 */

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id",
            unique = true)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = false, nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", unique = false, nullable = false)
    private Role role;

    public UserRole() {}

    public UserRole(User user, String role) {
        this.user = user;
        this.role.setRole(role);
    }

    public Long getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

