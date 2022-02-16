package com.apt.p2p.model.view;
import com.apt.p2p.entity.Cart;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartModel {
    private Integer id;

    private Integer quantity;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private ProductModel product;

    public CartModel(Cart entity) {
        this.id = entity.getId();
        this.quantity = entity.getQuantity();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();

        this.user = null;
        this.product = null;
    }
}
