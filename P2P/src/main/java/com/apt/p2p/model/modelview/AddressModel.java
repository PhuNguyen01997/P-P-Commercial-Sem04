package com.apt.p2p.model.modelview;

import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressModel {
    private Integer id;

    private String ownName;

    private String ownPhone;

    private String number;

    private String ward;

    private  String district;

    private String province;

    private UserModel user;

    private ShopModel shop;
}
