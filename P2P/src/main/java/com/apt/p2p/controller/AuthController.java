package com.apt.p2p.controller;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.UserModel;
import com.apt.p2p.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import javax.validation.Valid;
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
    public String signin() {
        return "user/auth/signin";
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth" + authentication);
        return "redirect:/";
    }

    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "user/auth/signup";
    }

    @PostMapping("/save-user")
    public String save(Model model,
                       @RequestParam("pic") MultipartFile image,
                       @Valid @ModelAttribute("user") UserModel userModel,
                       BindingResult result,
                       final RedirectAttributes attributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("user", userModel);
            model.addAttribute("hasAnyError", true);
            return "user/auth/signup";
        }
        String email = service.findByEmail(userModel.getEmail());

        if (email.equals(userModel.getEmail())) {
            model.addAttribute("duplicate", "Email already exists");
            model.addAttribute("hasAnyError", true);
            return "user/auth/signup";
        }
        User usr = new User();
        if (!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                File uploadFolder = ResourceUtils.getFile("classpath:static/img/auth");
                Path imagePath = Paths.get(uploadFolder.getPath(), image.getOriginalFilename());
                Files.write(imagePath, bytes);
                userModel.setAvatar(image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        usr.setEmail(userModel.getEmail());
        usr.setAvatar(userModel.getAvatar());
        usr.setPassword(passwordEncoder.encode(userModel.getPassword()));
        usr.setPhone(userModel.getPhone());
        usr.setUsername(userModel.getUsername());
        service.save(usr);
        return "redirect:/";
    }

    @GetMapping("reset-password")
    public String resetPassword() {
        return "user/auth/reset";
    }

    @GetMapping("forgot-password")
    public String forgotPassword() {
        return "user/auth/forgot";
    }
}
