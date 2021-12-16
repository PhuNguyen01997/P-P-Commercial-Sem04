package com.apt.p2p.controller;

import com.apt.p2p.entity.CardType;
import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.service.PaymentService;
import com.apt.p2p.validate.PaymentModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService service;

    @InitBinder("PaymentModel")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PaymentModelValidator());
    }


    @GetMapping("pay")
    public String payment() {
        return "user/main/payment";
    }

    @GetMapping("card")
    public String card(Model model) {
        model.addAttribute("card", new Payment());
        model.addAttribute("paymentTypes", Arrays.asList(CardType.values()));
        return "user/account/card";
    }

    @PostMapping("card")
    public String create(
            Model model,
            @Valid PaymentModel paymentModel,
            BindingResult result
    ) {
        PaymentModelValidator paymentModelValidator = new PaymentModelValidator();
        paymentModelValidator.validate(paymentModel, result);
        if(result.hasErrors()){
            model.addAttribute("card", paymentModel);
            return "user/account/card";
        }
        PaymentModel paymentResult = service.create(paymentModel);
        return "redirect:/card";
    }
}
