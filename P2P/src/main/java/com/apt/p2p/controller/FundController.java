package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FundController {
    @GetMapping("/portal/{id}/fund")
    public String portalIndex(Model model, @PathVariable("id") int shopId) {
        return "user/portal/fund";
    }
}
