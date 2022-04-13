package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @GetMapping("/admin/category")
    public String index(Model model) {
        return "admin/category";
    }
}
