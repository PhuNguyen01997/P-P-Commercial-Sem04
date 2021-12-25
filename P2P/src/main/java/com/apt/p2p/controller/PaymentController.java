package com.apt.p2p.controller;

import com.apt.p2p.entity.CardType;
import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.service.PaymentService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private PaymentService service;

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
        return "user/main/payment";
    }

    @GetMapping("card")
    public String card(Model model) {
        model.addAttribute("card", new PaymentModel());
        model.addAttribute("cards", service.findAllByUserId(1));

        return "user/account/card";
    }

    @PostMapping("card")
    public String create(
            Model model,
            @Valid @ModelAttribute("card") PaymentModel paymentModel,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            model.addAttribute("card", paymentModel);
            model.addAttribute("cards", service.findAll());
            model.addAttribute("hasAnyError", true);

            return "user/account/card";
        }
        PaymentModel paymentResult = service.create(paymentModel);
        return "redirect:/card";
    }

    @DeleteMapping("card/{id}")
    public String delete(
            @PathVariable("id") int id,
            RedirectAttributes redirectAttributes
    ) {
        boolean result = service.delete(id);
        if (result) {
            return "redirect:/card";
        }
        redirectAttributes.addFlashAttribute("globalError", "Can't delete this card, please try again later!");
        return "redirect:/card";
    }
}
