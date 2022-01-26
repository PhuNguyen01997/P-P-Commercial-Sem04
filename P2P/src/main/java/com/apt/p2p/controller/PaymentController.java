package com.apt.p2p.controller;

import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.CardModel;
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

        List<CartIndexViewModel> shopCarts = paymentService.processViewPayment(idList);
        model.addAttribute("shopCarts", shopCarts);
        model.addAttribute("addresses", addressService.findAllByUserId(3));
        model.addAttribute("creditCards", cardService.findAllByUserId(3));
        model.addAttribute("purchase", new PurchaseModel());

        return "user/main/payment";
    }

    @PostMapping("checkout")
    @Transactional
    public String checkout(@ModelAttribute("purchase") PurchaseModel purchaseModel,
                           RedirectAttributes redirectAttributes) {
        OrderModel result = orderService.create(purchaseModel);

        if (result == null) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra trong quá trình thanh toán, xin hãy thử lại sau");
            return "redirect:/cart";
        }

        cartService.deleteAllById(Arrays.asList(purchaseModel.getCartIds()));

        return "redirect:/order/" + result.getId();
    }
}
