package com.apt.p2p.model.view;

import com.apt.p2p.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private static String urlPath = "/assets/users/";

    private Integer id;

    @NotBlank(message = "field email is not null")
    private String email;

    @Pattern(regexp = "[^&%$#@!~]*" , message = "Username can't contain special characters")
    @NotBlank (message = "field username is not null")
    private String username;

    //    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&!-+=()])(?=\\S+$).{8,20}$"
//            , message = "Weak password")
    @NotBlank(message = "field password is not null")
    private String password;

    private boolean enabled = true;

    @Pattern(regexp = "^[\\d\\s]+$" , message = "invalid phone")
    @NotBlank(message = "field phone is not null")
    private String phone;

    private String avatar;

    private String stripeCustomerId;

    private String subName;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private ShopModel shop;

    private String birth;

    private String gender;

    private String provider;

    private List<AddressModel> addresses = new ArrayList<>();

    private List<CardModel> cards = new ArrayList<>();

    private List<CartModel> carts = new ArrayList<>();

    private List<RateModel> rates = new ArrayList<>();

    private List<OrderModel> orders = new ArrayList<>();

    public UserModel(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.stripeCustomerId = user.getStripeCustomerId();
        this.subName = user.getSubName();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.enabled = user.isEnabled();
        this.provider = user.getProvider();
    }

    public String toUrl(String fileName) {
        if(fileName == null || fileName.isEmpty()){
            return "/img/common/img_default_user.png";
        }
        return urlPath + fileName;
    }
}