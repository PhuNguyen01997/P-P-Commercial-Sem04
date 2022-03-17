package com.apt.p2p.model.view;

import com.apt.p2p.entity.Category;
import com.apt.p2p.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private Integer id;

    @NotBlank(message = "Tên sản phẩm không hợp lệ")
    private String name;

    @Min(value = 1000, message = "Giá tiền sản phẩm không hợp lệ (>1.000 vnđ & <100.000.000 vnđ)")
    @Max(value = 100000000, message = "Giá tiền sản phẩm không hợp lệ (>1.000 vnđ & <100.000.000 vnđ)")
    private BigDecimal price;

    private String image;

    @NotBlank(message = "Mô tả sản phẩm không hợp lệ")
    private String description;

    private int stock = 0;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private ShopModel shop;

    private List<RateModel> rates;

    private Category category;

    public ProductModel(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.stock = entity.getStock();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.category = entity.getCategory();

        this.shop = null;
        this.rates = null;
    }

    public String toUrl(String fileName){
        return "/assets/products/" + fileName;
    }
}
