package com.apt.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("signin")
    public String signin(){
        return "user/auth/signin";
    }

    @GetMapping("signup")
    public String signup(){
        return "user/auth/signup";
    }

    @GetMapping("reset-password")
    public String resetPassword(){
        return "user/auth/reset";
    }

    @GetMapping("forgot-password")
    public String forgotPassword(){
        return "user/auth/forgot";
    }
}
