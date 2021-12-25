package com.apt.p2p.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
