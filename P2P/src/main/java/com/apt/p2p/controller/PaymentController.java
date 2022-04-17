package com.apt.p2p.controller;

import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.CardModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.*;
import com.apt.p2p.validate.CardModelValidator;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CardService cardService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;

    @GetMapping("pay")
    public String payment() {
        return "redirect:/cart";
    }

    @PostMapping("pay")
    public String payment(Model model, @RequestParam("shopCart[]") String[] idList) {
        if (idList.length < 1) {
            return "redirect:/cart";
        }
        UserModel user = userService.getCurrentUser();

        List<CartIndexViewModel> shopCarts = paymentService.processViewPayment(idList);
        model.addAttribute("shopCarts", shopCarts);
        model.addAttribute("addresses", addressService.findAllByUserId(user.getId()));
        model.addAttribute("creditCards", cardService.findAllByUserId(user.getId()));
        model.addAttribute("purchase", new PurchaseModel());

        return "user/main/payment";
    }

    @PostMapping("checkout")
    public String checkout(@ModelAttribute("purchase") PurchaseModel purchaseModel,
                           RedirectAttributes redirectAttributes) {
        UserModel user = userService.getCurrentUser();
        List<OrderModel> result = orderService.create(user.getId(), purchaseModel);

        if (result == null) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra trong quá trình thanh toán, xin hãy thử lại sau");
            return "redirect:/cart";
        }

        cartService.deleteAllById(Arrays.asList(purchaseModel.getCartIds()));

        return "redirect:/order/";
    }
}
