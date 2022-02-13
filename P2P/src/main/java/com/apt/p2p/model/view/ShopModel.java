package com.apt.p2p.model.view;

import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
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

    private BigDecimal fund;

    private Boolean permission = false;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private UserModel user;

    private AddressModel address;

    private List<ProductModel> products;

    public List<OrderModel> orders;

    public Integer countProducts;

    public Integer countRates;

    public ShopModel(Shop shop) {
        this.id = shop.getId();
        this.logo = shop.getLogo();
        this.name = shop.getName();
        this.phone = shop.getPhone();
        this.permission = shop.getPermission();
        this.createdAt = shop.getCreatedAt();
        this.updatedAt = shop.getUpdatedAt();

        this.countProducts = shop.getProducts().size();
        this.countRates = shop.getProducts().stream().map(product -> product.getRates().size()).reduce(0, Integer::sum);
    }
}
