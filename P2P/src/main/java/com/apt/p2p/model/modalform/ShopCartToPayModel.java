package com.apt.p2p.model.modalform;

import com.apt.p2p.model.modelview.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopCartToPayModel {
    private int shopId;
    private List<Integer> carts;
}
