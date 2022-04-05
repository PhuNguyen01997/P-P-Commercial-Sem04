package com.apt.p2p.model.view;

import com.apt.p2p.entity.Category;
import com.apt.p2p.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private static String urlPath = "/assets/products/";

    private Integer id;

    @NotBlank(message = "Tên sản phẩm không hợp lệ")
    private String name;

    @Min(value = 1000, message = "Giá tiền sản phẩm không hợp lệ (>1.000 vnđ & <100.000.000 vnđ)")
    @Max(value = 100000000, message = "Giá tiền sản phẩm không hợp lệ (>1.000 vnđ & <100.000.000 vnđ)")
    @NotNull(message = "Giá sản phẩm không hợp lệ")
    private BigDecimal price;

    private List<String> image = new ArrayList<>();

    @NotBlank(message = "Mô tả sản phẩm không hợp lệ")
    private String description;

    private int stock = 0;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private ShopModel shop;

    private List<RateModel> rates = new ArrayList<>();

    private Category category;

    public ProductModel(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.stock = entity.getStock();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.category = entity.getCategory();

        try {
            ObjectMapper mapper = new ObjectMapper();
            this.image = mapper.readValue(entity.getImage(), new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.shop = null;
        this.rates = new ArrayList<>();
    }

    public ProductModel(ProductModel productModel) {
        this.id = productModel.getId();
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        this.image = productModel.getImage();
        this.description = productModel.getDescription();
        this.stock = productModel.getStock();
        this.createdAt = productModel.getCreatedAt();
        this.updatedAt = productModel.getUpdatedAt();
        this.shop = productModel.getShop();
        this.rates = productModel.getRates();
        this.category = productModel.getCategory();
    }

    public String toUrl(String fileName) {
        return urlPath + fileName;
    }

    public String getImageName(String fullFileName){
        return fullFileName.replaceAll("\\.\\w+$", "");
    }
}
