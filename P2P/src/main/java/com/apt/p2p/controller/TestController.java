package com.apt.p2p.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Value("stripe.publish.key")
    private String publishKey;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("amount", 50 * 100);
        model.addAttribute("stripePublishKey", publishKey);
        model.addAttribute("currency", "USD");
        return "user/main/test.html";
    }
}
