package com.apt.p2p.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @Column(name = "roleId")
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "primaryKey.role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRole> userRoles;
}
