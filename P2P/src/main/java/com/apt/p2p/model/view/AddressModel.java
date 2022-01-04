package com.apt.p2p.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressModel {
    private Integer id;

    @NotBlank(message = "Họ tên không thể trống")
    private String ownName;

    @NotBlank(message = "Số điện thoại không thể trống")
    @Pattern(regexp = "^[\\d\\s\\-\\.]+$", message = "Số điện thoại không hợp lệ")
    private String ownPhone;

    @NotBlank(message = "Số địa chỉ không thể trống")
    private String number;

    private String ward;

    private  String district;

    private String province;

    private UserModel user;

    private ShopModel shop;
}
