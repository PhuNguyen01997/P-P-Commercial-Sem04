package com.apt.p2p.controller;

import com.apt.p2p.model.view.AdminHeaderNavi;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String adminIndex(Model model) {
        List<UserModel> users = userService.findAll();
        model.addAttribute("users", users);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Người dùng", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Người dùng", naviArr));

        return "admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String adminIndex(Model model, @PathVariable("id") int userId) {
        UserModel user = userService.findById(userId);
        model.addAttribute("user", user);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Người dùng", "/admin/user"},
                new String[]{user.getUsername(), ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Người dùng", naviArr));

        return "admin/user-detail";
    }
}
