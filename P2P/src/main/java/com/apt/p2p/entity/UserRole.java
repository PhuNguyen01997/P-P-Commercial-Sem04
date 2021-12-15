package com.apt.p2p.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserRole")
public class UserRole implements Serializable {
    @Id
    @Column(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    private Role role;
}
