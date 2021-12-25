package com.apt.p2p.controller;

import com.apt.p2p.entity.User;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AuthController {
    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("signin")
    public String signin(){
        return "user/auth/signin";
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth" + authentication);
        return "redirect:/";
    }

    @GetMapping("signup")
    public String signup(Model model){
        model.addAttribute("user" , new User());
        return "user/auth/signup";
    }

    @PostMapping("/save-user")
    public String save(Model model
                        ,@ModelAttribute("user") User user
                        ,@RequestParam("pic") MultipartFile image
                        , BindingResult result,
                        final RedirectAttributes attributes
                        ) {

        System.out.println("information: " + user);

        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                File uploadFolder = ResourceUtils.getFile("classpath:static/img/auth");
                Path imagePath = Paths.get(uploadFolder.getPath(), image.getOriginalFilename());
                Files.write(imagePath, bytes);
                user.setAvatar(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (user.getUserId() != 0) {
                service.findById(user.getUserId()).ifPresent(p -> user.setAvatar(p.getAvatar()));
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.save(user);
        return "redirect:/";
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
