package com.apt.p2p.model.modelview;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartIndexViewModel {
    private ShopModel shop;
    private List<ProductCartModel> productCarts;
}