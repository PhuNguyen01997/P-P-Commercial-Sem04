package com.apt.p2p.controller;

import com.apt.p2p.model.form.WithdrawForm;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class FundController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @GetMapping("portal/fund")
    public String portalIndex(Model model) {
        int userId = 2;
        UserModel user = userService.findById(userId);

        model.addAttribute("shop", user.getShop() != null ? shopService.findById(user.getShop().getId()) : null);
        return "user/portal/fund";
    }

    @GetMapping("portal/fund/withdraw")
    public String portalWithdraw(Model model) {
        int userId = 2;
        UserModel user = userService.findById(userId);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : null);
        model.addAttribute("withdraw", new WithdrawForm(user.getShop() != null ? user.getShop().getId() : 0 , BigDecimal.ZERO));
        return "user/portal/fund-withdraw";
    }

    @PostMapping("portal/fund/withdraw")
    public String portalWithdraw(Model model,
                                 @Valid @ModelAttribute("withdraw") WithdrawForm input,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("shop", shopService.findById(input.getShopId()));
            model.addAttribute("withdraw", input);
            return "user/portal/fund-withdraw";
        }

        boolean success = shopService.shopWithDraw(input.getShopId(), input.getAmount());
        if(!success){
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra trong quá trình rút tiền, xin hãy thử lại sau");

            model.addAttribute("shop", shopService.findById(input.getShopId()));
            model.addAttribute("withdraw", input);
            return "user/portal/fund-withdraw";
        }

        return "redirect:/portal/" + input.getShopId() + "/fund/";
    }
}
