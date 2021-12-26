package com.apt.p2p.model.modelview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private Integer id;

    private String name;

    private Double price;

    private String image;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private ShopModel shop;

    private List<RateModel> rates;
}
