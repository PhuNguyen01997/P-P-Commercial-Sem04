package com.apt.p2p.service;

import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.CartModel;

import java.util.List;

public interface CartService {
    List<CartModel> findByUserId(int userId);

    void delete(int id);

    void deleteAllById(List<Integer> id);

    boolean edit(CartModel cartModel);

    CartModel save(ProductAddCartModel productAddCartModel);

    List<CartIndexViewModel> getCartListChunkByShop();

    CartIndexViewModel getCartProductByShopIdAndCartId(int shopId, List<Integer> cartIdList);
}
