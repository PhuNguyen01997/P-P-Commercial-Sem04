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
    private int id;

    private String logo;

    @NotBlank(message = "Tên cửa hàng không hợp lệ")
    private String name;

    @NotBlank(message = "Số điện thoại không hợp lệ")
    private String phone;

    private BigDecimal fund = BigDecimal.ZERO;

    private Boolean permission = false;

    @NotBlank(message = "Mô tả cửa hàng không hợp lệ")
    private String description;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private UserModel user;

    private AddressModel address;

    private List<ProductModel> products;

    public List<OrderModel> orders;

    public List<ShopTransactionModel> shopTransactions;

    public Integer countProducts = 0;

    public Integer countRates = 0;

    public ShopModel(Shop shop) {
        this.id = shop.getId();
        this.logo = shop.getLogo();
        this.name = shop.getName();
        this.phone = shop.getPhone();
        this.permission = shop.getPermission();
        this.description = shop.getDescription();
        this.createdAt = shop.getCreatedAt();
        this.updatedAt = shop.getUpdatedAt();

        this.user = null;
        this.address = null;
        this.products = null;
        this.orders = null;
        this.shopTransactions = null;

        this.countProducts = shop.getProducts().size();
        this.countRates = shop.getProducts().stream().map(product -> product.getRates().size()).reduce(0, Integer::sum);
    }
}
