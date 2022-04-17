package com.apt.p2p.controller;

import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.CartModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.CartService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(Model model) {
        UserModel user = userService.getCurrentUser();

        List<CartIndexViewModel> cartList = cartService.getCartListChunkByShop(user.getId());
        model.addAttribute("cartList", cartList);

        return "user/main/cart";
    }

    @PostMapping("/cart-add")
    public String addCart(Model model, @ModelAttribute("addCartModel") ProductAddCartModel cartModel, RedirectAttributes redirectAttributes) {
        UserModel user = userService.getCurrentUser();

        try {
            cartService.save(user.getId(), cartModel);
            redirectAttributes.addFlashAttribute("globalSuccess", "Add to cart successful !!!");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("globalError", e.getMessage());
        }

        return "redirect:/product/" + cartModel.getProductId();
    }

    @PostMapping("/cart-edit")
    public ResponseEntity editCart(@ModelAttribute("cartModel") CartModel cartModel) {
        Boolean success = cartService.edit(cartModel);
        return new ResponseEntity(success.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public String deleteCart(Model model, @PathVariable("id") int id) {
        cartService.delete(id);
        return "redirect:/cart";
    }

    @DeleteMapping("/cart")
    public String deleteCart(Model model, @RequestParam(value = "ids[]", required = false) Integer[] ids) {
        cartService.deleteAllById(Arrays.stream(ids).collect(Collectors.toList()));
        return "redirect:/cart";
    }
}
