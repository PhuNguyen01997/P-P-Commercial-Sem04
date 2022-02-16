package com.apt.p2p.controller;

import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.ProductService;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RateService rateService;
    @Autowired
    private ShopService shopService;

    @GetMapping("product/{id}")
    public String productDetail(Model model, @PathVariable("id") int id) {
        ProductModel product = productService.findById(id);

        List<RateModel> rates = rateService.findByProductId(id);

        ShopModel shop = shopService.findByProductId(id);
        shop.setCountProducts(productService.countByShopId(shop.getId()));
        shop.setCountRates(rateService.countByShopId(shop.getId()));

        model.addAttribute("product", product);
        model.addAttribute("rates", rates);
        model.addAttribute("shop", shop);
        model.addAttribute("addCartModel", new ProductAddCartModel(1, id));

        return "user/main/product-detail";
    }

    @GetMapping("portal/{id}/product")
    public String portalProduct(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/product";
    }

    @GetMapping("portal/{id}/product/create")
    public String portalProductCreate(Model model, @PathVariable("id") int shopId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/product-form";
    }

    @GetMapping("portal/{shopId}/product/{productId}")
    public String portalProductCreate(Model model, @PathVariable("shopId") int shopId, @PathVariable("productId") int productId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/product-form";
    }
}
