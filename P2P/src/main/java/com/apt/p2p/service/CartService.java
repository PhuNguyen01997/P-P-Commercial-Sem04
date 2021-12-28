package com.apt.p2p.service;

import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.CartIndexViewModel;
import com.apt.p2p.model.modelview.CartModel;

import java.util.List;

public interface CartService {
    List<CartModel> findByUserId(int userId);

    CartModel save(ProductAddCartModel productAddCartModel);

    List<CartIndexViewModel> getCartListChunkByShop();

    void delete(int id);

    void deleteAllById(List<Integer> id);
}
