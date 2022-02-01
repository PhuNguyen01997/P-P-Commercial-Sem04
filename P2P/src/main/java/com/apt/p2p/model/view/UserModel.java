package com.apt.p2p.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    private Integer id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String phone;

    private String avatar;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private String stripeCustomerId;

    private ShopModel shop;

    private List<AddressModel> addresses;

    private List<CardModel> cards;

    private List<CartModel> carts;

    private List<RateModel> rates;

    private List<OrderModel> orders;
}
