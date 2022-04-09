package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//
//@Entity
//@Table (name = "user_role")
@AssociationOverrides({ @AssociationOverride(name = "primaryKey.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "primaryKey.role", joinColumns = @JoinColumn(name = "role_id")) })
public class UserRole {
    public UserRole() {
    }

    public UserRole(UserRoleId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @EmbeddedId
    private UserRoleId primaryKey;

    public UserRoleId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserRoleId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Role getRole() {
        return getPrimaryKey().getRole();
    }

    public void setRole(Role role) {
        getPrimaryKey().setRole(role);
    }

    @Transient
    public User getUser() {
        return getPrimaryKey().getUser();
    }

    public void setType(User user) {
        getPrimaryKey().setUser(user);
    }
}
