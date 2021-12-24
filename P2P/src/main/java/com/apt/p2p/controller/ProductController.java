package com.apt.p2p.controller;

import com.apt.p2p.model.ProductModel;
import com.apt.p2p.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    @Autowired
    private ProductServiceImpl service;

    @GetMapping("product/{id}")
    public String productDetail(Model model, @PathVariable int id) {
        ProductModel test = service.findById(id);
        model.addAttribute("product", new ProductModel());
        return "user/main/product-detail";
    }
}
