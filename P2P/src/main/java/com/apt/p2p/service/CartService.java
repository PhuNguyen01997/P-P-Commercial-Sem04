package com.apt.p2p.service;

import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.CartIndexViewModel;
import com.apt.p2p.model.modelview.CartModel;

import java.util.List;

public interface CartService {
    public List<CartModel> findByUserId(int userId);

    public CartModel save(ProductAddCartModel productAddCartModel);

    public List<CartIndexViewModel> getCartListChunkByShop();
}
