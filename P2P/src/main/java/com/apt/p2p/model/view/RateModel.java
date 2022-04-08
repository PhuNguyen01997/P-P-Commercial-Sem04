package com.apt.p2p.model.view;

import com.apt.p2p.entity.Rate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateModel {
    private Integer id;

    private String description;

    private Integer star;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private ProductModel product;

    public RateModel(Rate entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.star = entity.getStar();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();

        this.user = null;
        this.product = null;
    }
}
