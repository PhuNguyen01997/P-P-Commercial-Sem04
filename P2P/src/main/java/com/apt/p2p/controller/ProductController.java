package com.apt.p2p.controller;

import com.apt.p2p.model.form.*;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.CategoryService;
import com.apt.p2p.service.ProductService;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.validate.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @InitBinder("productForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProductFormValidator());
    }

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(required = false, name = "keyword") String keyword,
                        @RequestParam(required = false, name = "minPrice") BigDecimal minPrice,
                        @RequestParam(required = false, name = "maxPrice") BigDecimal maxPrice,
                        @RequestParam(required = false, name = "rate") Integer rate,
                        @RequestParam(required = false, name = "sortBy") String sortBy,
                        @RequestParam(required = false, name = "sortDirection") Boolean sortDirection) {


        model.addAttribute("products", productService.SonFindAllWithFilter(keyword, minPrice, maxPrice, rate, sortBy, sortDirection));
        return "user/main/index";
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
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("categories", categoryService.findAll());
        return "user/portal/product-form";
    }

    @PostMapping("portal/product")
    public String portalProductCreate(Model model,
                                      @Valid @ModelAttribute("productForm") ProductForm product,
                                      BindingResult productResult) {
        int shopId = 2;
        if (productResult.hasErrors()) {
            model.addAttribute("shop", shopService.findById(shopId));
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.findAll());
            return "user/portal/product-form";
        }

        ProductModel newProduct = null;

        if (product.getId() == null) {
            newProduct = productService.create(product, shopId);
        } else {
            newProduct = productService.update(product);
            String str = "";
        }

        if (newProduct == null) {
            model.addAttribute("shop", shopService.findById(shopId));
            model.addAttribute("productForm", new ProductModel());
            model.addAttribute("categories", categoryService.findAll());
            return "user/portal/product-form";
        }
        return "redirect:/portal/product/" + newProduct.getId();
    }

    @GetMapping("portal/product/{productId}")
    public String portalProductCreate(Model model, @PathVariable("productId") int productId) {
        int shopId = 2;
        ShopModel shop = shopService.findById(shopId);
        List<ProductModel> productFind = shop.getProducts().stream().filter(p -> p.getId() == productId).collect(Collectors.toList());

        if (productFind.isEmpty()) {
            return "notfound";
        }

        ProductModel product = productService.findByShopIdAndProductId(shopId, productId);

        model.addAttribute("shop", shopService.findById(shopId));
        model.addAttribute("productForm", new ProductForm(product));
        model.addAttribute("categories", categoryService.findAll());
        return "user/portal/product-form";
    }

    @PostMapping("/api/products/portal")
    @ResponseBody
    public List<ProductModel> portalApiIndex(@RequestBody FilterProductPortal input) {
        int shopId = 2;
        return productService.findAllByShopWithFilterPortal(shopId, input);
    }
}
