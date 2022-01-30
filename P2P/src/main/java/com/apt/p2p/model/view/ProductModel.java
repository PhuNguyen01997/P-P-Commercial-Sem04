package com.apt.p2p.model.view;

import com.apt.p2p.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private Integer id;

    private String name;

    private BigDecimal price;

    private String image;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private ShopModel shop;

    private List<RateModel> rates;

    private Category category;
}
