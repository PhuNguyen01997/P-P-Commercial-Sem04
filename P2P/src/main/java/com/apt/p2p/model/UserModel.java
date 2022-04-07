package com.apt.p2p.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {

    @NotBlank(message = "field email is not null")
    private String email;

    @Pattern(regexp = "[^&%$#@!~]*" , message = "Username can't contain special characters")
    @NotBlank (message = "field username is not null")
    private String username;

    private boolean enabled = true;

//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!-+=()])(?=\\S+$).{8,20}$"
//            , message = "Weak password")
    @NotBlank(message = "field password is not null")
    private String password;

    @Pattern(regexp = "^[\\d\\s]+$" , message = "invalid phone")
    @NotBlank(message = "field phone is not null")
    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

}
