package com.apt.p2p.controller;

import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.PaymentModel;
import com.apt.p2p.service.*;
import com.apt.p2p.validate.PaymentModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;

    @InitBinder("card")
    protected void initBinder(WebDataBinder binder) {
        // trim at start and end String
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);

        System.out.println("A binder for object: " + binder.getObjectName());
        binder.addValidators(new PaymentModelValidator());
    }

    @GetMapping("pay")
    public String payment() {
        return "redirect:/cart";
    }

    @PostMapping("pay")
    public String payment(Model model,
                          @RequestParam("shopCart[]") String[] idList) {
        if (idList.length < 1) {
            return "redirect:/cart";
        }

        List<CartIndexViewModel> shopCarts = paymentService.processViewPayment(idList);
        model.addAttribute("shopCarts", shopCarts);
        model.addAttribute("addresses", addressService.findAllByUserId(1));
        model.addAttribute("creditCards", paymentService.findAllByUserId(1));
        model.addAttribute("purchase", new PurchaseModel());

        return "user/main/payment";
    }

    @GetMapping("card")
    public String card(Model model) {
        model.addAttribute("card", new PaymentModel());
        model.addAttribute("cards", paymentService.findAllByUserId(1));

        return "user/account/card";
    }

    @PostMapping("card")
    public String create(
            Model model,
            @Valid @ModelAttribute("card") PaymentModel paymentModel,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("card", paymentModel);
            model.addAttribute("cards", paymentService.findAll());
            model.addAttribute("hasAnyError", true);

            return "user/account/card";
        }
        PaymentModel paymentResult = paymentService.create(paymentModel);
        if(paymentResult == null){
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra không thêm thẻ thanh toán, xin hãy thử lại sau");
        }
        return "redirect:/card";
    }

    @DeleteMapping("card/{id}")
    public String delete(
            @PathVariable("id") int id,
            RedirectAttributes redirectAttributes
    ) {
        boolean result = paymentService.delete(id);
        if (!result) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra không thể xóa, xin hãy thử lại sau");
        }
        return "redirect:/card";
    }

    @PostMapping("checkout")
    public String checkout(@ModelAttribute("purchase") PurchaseModel purchaseModel,
                           RedirectAttributes redirectAttributes) {
        OrderModel result = orderService.create(purchaseModel);
        if(result == null){
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra trong quá trình thanh toán, xin hãy thử lại sau");
            return "redirect:/cart";
        }
        return "redirect:/order/" + result.getId();
    }
}
