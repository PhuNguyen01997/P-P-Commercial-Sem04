package com.apt.p2p.model.view;

import com.apt.p2p.entity.Address;
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
public class ShopModel {
    private Integer id;

    @NotBlank
    private String logo;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    private Boolean permission = false;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private UserModel user;

    private AddressModel address;

    private List<ProductModel> products;

    public List<OrderModel> orders;

    public ShopFundModel shopFund;

    public Integer countProducts;

    public Integer countRates;
}
