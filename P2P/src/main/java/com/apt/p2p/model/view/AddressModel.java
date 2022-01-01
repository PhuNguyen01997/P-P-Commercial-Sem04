package com.apt.p2p.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
