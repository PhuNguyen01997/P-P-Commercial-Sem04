package com.apt.p2p.controller;

import com.apt.p2p.model.form.FilterProductPortal;
import com.apt.p2p.model.form.ProductAddCartModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.CategoryService;
import com.apt.p2p.service.ProductService;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.validate.PicturesValidator;
import com.apt.p2p.validate.ShopPictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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

    @InitBinder("imageFiles")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PicturesValidator());
    }

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
        model.addAttribute("product", new ProductModel());
        model.addAttribute("categories", categoryService.findAll());
        return "user/portal/product-form";
    }

    @PostMapping("portal/product/create")
    public String portalProductCreate(Model model,
                                      @Valid @ModelAttribute ProductModel product,
                                      BindingResult productResult,
                                      @Valid @RequestParam("imageFiles") MultipartFile[] pictures,
                                      BindingResult imageResult,
                                      @RequestParam("category") int categoryId) {
        int shopId = 2;
        if (productResult.hasErrors() || imageResult.hasErrors()) {
            model.addAttribute("shop", shopService.findById(shopId));
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.findAll());
        }

        ProductModel newProduct = productService.create(product, categoryId);

        if (newProduct == null) {
            model.addAttribute("shop", shopService.findById(shopId));
            model.addAttribute("product", new ProductModel());
            model.addAttribute("categories", categoryService.findAll());
            return "user/portal/product-form";
        }
        return "redirect:/portal/product/" + newProduct.getId();
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
