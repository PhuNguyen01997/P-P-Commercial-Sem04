package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.model.form.*;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ResponsePagiView;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.service.*;
import com.apt.p2p.validate.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private LocationService locationService;
    @Autowired
    private ProductMapper productMapper;

    @InitBinder("productForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProductFormValidator());
    }

    @GetMapping("")
    public String index(Model model,
                        @ModelAttribute FilterProductIndex filterModel,
                        @ModelAttribute PagiSortModel pagiSortModel) {
        pagiSortModel.setSize(10);
        Page<Product> pageProducts = productService.findAllByShopWithFilterIndex(filterModel, pagiSortModel);
        List<ProductModel> products = pageProducts.stream()
                .map(e -> {
                    ProductModel pModel = productMapper.productEntityToModel(e);
                    pModel.setShop(shopService.findById(pModel.getShop().getId()));
                    return pModel;
                })
                .collect(Collectors.toList());

        model.addAttribute("products", products);

        model.addAttribute("filterModel", filterModel);
        model.addAttribute("locations", locationService.provinceFindAll());

        ResponsePagiView pagiView = new ResponsePagiView(pagiSortModel.getPage(), pageProducts.getTotalPages());
        model.addAttribute("pagiView", pagiView);

        return "user/main/index";
    }

    @GetMapping("product/{id}")
    public String productDetail(Model model, @PathVariable("id") int id) {
        ProductModel product = productService.findById(id);
        model.addAttribute("product", product);

        ShopModel shop = shopService.findByProductId(id);
        shop.setCountProducts(productService.countByShopId(shop.getId()));
        shop.setCountRates(rateService.countByShopId(shop.getId()));
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
        }

        if (newProduct == null) {
            model.addAttribute("shop", shopService.findById(shopId));
            model.addAttribute("productForm", product);
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
