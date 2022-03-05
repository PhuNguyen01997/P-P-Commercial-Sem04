package com.apt.p2p.controller;

import com.apt.p2p.model.form.ImageFilesModels;
import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.AddressService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UserService;
import com.apt.p2p.validate.PictureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;


@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @InitBinder("imageFiles")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PictureValidator());
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

        if(resultShop.hasErrors() || resultImages.hasErrors()){
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
