package com.apt.p2p.model.view;

import com.apt.p2p.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private String stripeCustomerId;

    private ShopModel shop;

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
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.stripeCustomerId = user.getStripeCustomerId();
    }

    public String toUrl(String fileName) {
        return urlPath + fileName;
    }
}
