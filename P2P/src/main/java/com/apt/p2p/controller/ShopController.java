package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.ProductMapper;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.model.form.FilterProductIndex;
import com.apt.p2p.model.form.ImageFilesModels;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.*;
import com.apt.p2p.service.*;
import com.apt.p2p.validate.PictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ShopController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProductMapper productMapper;

    @InitBinder("imageFiles")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PictureValidator());
    }

    @GetMapping("shop/{shopId}")
    public String shopIndex(Model model,
                            @PathVariable("shopId") int shopId,
                            @ModelAttribute FilterProductIndex filterModel,
                            @ModelAttribute PagiSortModel pagiSortModel) {
        ShopModel shop = shopService.findById(shopId);
        model.addAttribute("shop", shop);

        pagiSortModel.setSize(10);
        filterModel.setShopId(shopId);
        Page<Product> pageProducts = productService.findAllByShopWithFilterIndex(filterModel, pagiSortModel);
        List<ProductModel> products = pageProducts.stream()
                .map(e -> {
                    ProductModel pModel = productMapper.productEntityToModel(e);
                    pModel.setShop(shop);
                    return pModel;
                })
                .collect(Collectors.toList());
        model.addAttribute("products", products);

        model.addAttribute("filterModel", filterModel);
        model.addAttribute("locations", locationService.provinceFindAll());

        ResponsePagiView pagiView = new ResponsePagiView(pagiSortModel.getPage(), pageProducts.getTotalPages());
        model.addAttribute("pagiView", pagiView);

        return "user/main/shop-detail";
    }

    @GetMapping("admin/shop")
    public String adminShopIndex(Model model) {
        List<ShopModel> shops = shopService.findAll();
        model.addAttribute("shops", shops);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Cửa hàng", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Cửa hàng", naviArr));

        return "admin/shop";
    }

    @GetMapping("admin/shop/{shopId}")
    public String adminShopDetail(Model model, @PathVariable("shopId") int shopId) {
        ShopModel shop = shopService.findById(shopId);
        model.addAttribute("shop", shop);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Cửa hàng", "/admin/shop"},
                new String[]{shop.getName(), ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Cửa hàng", naviArr));

        return "admin/shop-detail";
    }

    @GetMapping("portal")
    public String shopDetail(Model model) {
        int userId = 3;
        UserModel user = userService.findById(userId);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : new ShopModel());
        model.addAttribute("user", user);
        model.addAttribute("imageFiles", null);
        return "user/portal/shop-form";
    }

    @PostMapping("portal")
    public String updateShop(Model model,
                             @RequestParam("teAddress") int addressId,
                             @Valid @ModelAttribute("shop") ShopModel shop,
                             BindingResult resultShop,
                             @Valid @ModelAttribute("imageFiles") ImageFilesModels imageFilesModels,
                             BindingResult resultImages) {
        int userId = 3;
        UserModel user = userService.findById(userId);
        AddressModel address = addressService.findById(addressId);
        shop.setAddress(address);
        shop.setUser(user);

        if (resultShop.hasErrors() || resultImages.hasErrors()) {
            model.addAttribute("shop", shop);
            model.addAttribute("user", user);
            model.addAttribute("imageFiles", imageFilesModels);
            return "user/portal/shop-form";
        }

        MultipartFile logoFile = imageFilesModels.getImageFiles()[0];
        MultipartFile backgroundFile = imageFilesModels.getImageFiles()[1];

        ShopModel shopModel = shopService.createOrUpdate(shop, logoFile, backgroundFile);

        return "redirect:/portal";
    }
}
