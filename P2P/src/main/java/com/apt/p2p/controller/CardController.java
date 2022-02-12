package com.apt.p2p.controller;

import com.apt.p2p.model.view.CardModel;
import com.apt.p2p.service.CardService;
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

import javax.validation.Valid;

@Controller
public class CardController {
    @Autowired
    private CardService cardService;

    @InitBinder("card")
    protected void initBinder(WebDataBinder binder) {
        // trim at start and end String
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);

        System.out.println("A binder for object: " + binder.getObjectName());
        binder.addValidators(new CardModelValidator());
    }

    @GetMapping("card")
    public String card(Model model) {
        model.addAttribute("card", new CardModel());
        model.addAttribute("cards", cardService.findAllByUserId(3));

        return "user/account/card";
    }

    @PostMapping("card")
    public String create(
            Model model,
            @Valid @ModelAttribute("card") CardModel cardModel,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("card", cardModel);
            model.addAttribute("cards", cardService.findAllByUserId(3));
            model.addAttribute("hasAnyError", true);

            return "user/account/card";
        }
        try {
            CardModel paymentResult = cardService.create(cardModel);
        } catch (StripeException e) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra, vui lòng kiểm tra lại thông tin thẻ");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra không thêm thẻ thanh toán, xin hãy thử lại sau");
        }
        return "redirect:/card";
    }

    @DeleteMapping("card/{stripeCardId}")
    public String delete(
            @PathVariable("stripeCardId") String stripeCardId,
            RedirectAttributes redirectAttributes
    ) {
        boolean result = cardService.delete(stripeCardId);
        if (!result) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra không thể xóa, xin hãy thử lại sau");
        }
        return "redirect:/card";
    }
}
