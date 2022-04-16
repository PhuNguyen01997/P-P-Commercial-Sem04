package com.apt.p2p.model.view;

import com.apt.p2p.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

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

    private String district;

    private String province;

    private String wardId;

    private int districtId;

    private int provinceId;

    private UserModel user;

    private ShopModel shop;

    private List<OrderModel> orders;

    public AddressModel(Address address) {
        this.id = address.getId();
        this.ownName = address.getOwnName();
        this.ownPhone = address.getOwnPhone();
        this.number = address.getNumber();
        this.ward = address.getWard();
        this.district = address.getDistrict();
        this.province = address.getProvince();
        this.wardId = address.getWardId();
        this.districtId = address.getDistrictId();
        this.provinceId = address.getProvinceId();

        this.orders = new ArrayList<>();
    }

    public String getFullAddress(){
        StringBuilder stringBuilder = new StringBuilder(this.number);
        stringBuilder.append(", " + this.ward);
        stringBuilder.append(", " + this.district);
        stringBuilder.append(", " + this.province);
        return stringBuilder.toString();
    }
}
