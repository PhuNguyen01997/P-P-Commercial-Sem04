package com.apt.p2p.model.view;

import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopModel {
    private Integer id;

    private String logo;

    private String background;

    @NotBlank(message = "Invalid store name")
    private String name;

    @NotBlank(message = "invalid phone number")
    private String phone;

    private BigDecimal fund = BigDecimal.ZERO;

    private Boolean permission = false;

    @NotBlank(message = "Invalid store description")
    private String description;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private String stripeCardId;

    private UserModel user;

    private AddressModel address;

    private List<ProductModel> products = new ArrayList<>();

    public List<OrderModel> orders  = new ArrayList<>();

    public List<ShopTransactionModel> shopTransactions;

    public Integer countProducts = 0;

    public Integer countRates = 0;

    public Double averageRates = 0.0;

    public ShopModel(Shop shop) {
        this.id = shop.getId();
        this.logo = shop.getLogo();
        this.background = shop.getBackground();
        this.name = shop.getName();
        this.phone = shop.getPhone();
        this.permission = shop.getPermission();
        this.description = shop.getDescription();
        this.createdAt = shop.getCreatedAt();
        this.updatedAt = shop.getUpdatedAt();
        this.stripeCardId = shop.getStripeCardId();
        this.fund = shop.getFund();

        this.user = null;
        this.address = null;
        
        this.products = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.shopTransactions = new ArrayList<>();

        if(shop.getProducts() != null && shop.getProducts().size() > 0){
            this.countProducts = shop.getProducts().size();
            this.countRates = shop.getProducts().stream().map(product -> product.getRates().size()).reduce(0, Integer::sum);

            List<Rate> allRateOfShop = new ArrayList<>();
            shop.getProducts().forEach(product -> {
                allRateOfShop.addAll(product.getRates());
            });
            this.averageRates = allRateOfShop.stream().mapToDouble(rate -> rate.getStar()).average().orElse(0);
        }
    }

    public String toUrl(String fileName){
        return "/assets/shops/" + fileName;
    }
}
