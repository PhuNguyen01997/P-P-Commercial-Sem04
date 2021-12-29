package com.apt.p2p.controller;

import com.apt.p2p.model.modalform.ProductAddCartModel;
import com.apt.p2p.model.modelview.CartIndexViewModel;
import com.apt.p2p.model.modelview.CartModel;
import com.apt.p2p.service.CartService;
import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ShopService shopService;

    @GetMapping("/cart")
    public String cart(Model model){
        List<CartIndexViewModel> cartList = cartService.getCartListChunkByShop();

        model.addAttribute("cartList", cartList);

        return "user/main/cart";
    }

    @PostMapping("/cart-add")
    public String addCart(Model model, @ModelAttribute("addCartModel") ProductAddCartModel cartModel){
        cartService.save(cartModel);
        return "redirect:/product/" + cartModel.getProductId();
    }

    @PostMapping("/cart-edit")
    public ResponseEntity editCart(@ModelAttribute("cartModel") CartModel cartModel){
        Boolean success = cartService.edit(cartModel);
        return new ResponseEntity(success.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public String deleteCart(Model model, @PathVariable("id") int id){
        cartService.delete(id);
        return "redirect:/cart";
    }

    @DeleteMapping("/cart")
    public String deleteCart(Model model, @RequestParam(value = "ids[]") Integer[] ids){
        cartService.deleteAllById(Arrays.stream(ids).collect(Collectors.toList()));
        return "redirect:/cart";
    }
}
