package com.apt.p2p.model.modelview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartModel {
    private Integer id;

    private Integer quantity;

    private Date createdAt;

    private Date updatedAt;

    private UserModel user;

    private ProductModel product;
}
