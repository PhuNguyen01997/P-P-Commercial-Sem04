package com.apt.p2p.model;

import com.apt.p2p.entity.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private String email;

    private String username;

    private boolean enabled = true;

    private String password;

    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

}
