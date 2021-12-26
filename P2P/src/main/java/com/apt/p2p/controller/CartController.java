package com.apt.p2p.controller;

import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.CartIndexViewModel;
import com.apt.p2p.service.CartService;
import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ShopService shopService;

    @GetMapping("/cart")
    public String cart(Model model){
        List<CartIndexViewModel> cartList = cartService.getCartListChunkByShop();
        return "user/main/cart";
    }

    @PostMapping("/cart-add")
    public String addCart(Model model, @ModelAttribute("addCartModel") ProductAddCartModel cartModel){
        cartService.save(cartModel);
        return "redirect:/product/" + cartModel.getProductId();
    }
}
