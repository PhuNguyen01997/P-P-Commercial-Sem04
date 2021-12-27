package com.apt.p2p.model.modelview;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductCartModel{
    private ProductModel product;
    private CartModel cart;
}