package com.apt.p2p.controller;

import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.form.FilterShopTransaction;
import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.CategoryService;
import com.apt.p2p.service.ProductService;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
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

    @GetMapping("portal/product")
    public String portalProduct(Model model) {
        int shopId = 2;
        model.addAttribute("shop", shopService.findById(shopId));
        model.addAttribute("categories", categoryService.findAll());
        return "user/portal/product";
    }

    @GetMapping("portal/product/create")
    public String portalProductCreate(Model model) {
        int shopId = 2;
        model.addAttribute("shop", shopService.findById(shopId));
        model.addAttribute("products", productService.findAllByShopId(shopId));
        return "user/portal/product-form";
    }

    @GetMapping("portal/{shopId}/product/{productId}")
    public String portalProductCreate(Model model, @PathVariable("shopId") int shopId, @PathVariable("productId") int productId) {
        model.addAttribute("shop", shopService.findById(shopId));
        return "user/portal/product-form";
    }

    @PostMapping("/api/products/portal")
    @ResponseBody
    public List<ProductModel> portalApiIndex(@RequestBody FilterProductPortal input) {
        int shopId = 2;
        return productService.findAllByShopWithFilterPortal(shopId, input);
    }
}
