package com.apt.p2p.controller;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.AdminHeaderNavi;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.UserRepository;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UserService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UsersDetailServiceImpl usersDetailService;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private String imgUploadDir = "users/";

    @GetMapping("/account")
    public String userEdit(Model model) {
        UserModel user = usersDetailService.getCurrentUser();
        User user1 = userRepository.findByUsername(user.getUsername());
        List<String> gender = new ArrayList<>();
        gender.add("Man");
        gender.add("Female");
        gender.add("Other");
        user.setGender(user1.getGender());
        model.addAttribute("user", user);
        model.addAttribute("user1", user1);
        model.addAttribute("listGender", gender);
        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : null);
        return "user/account/user-form";
    }

    @GetMapping("/change-password")
    public String changePassword(Model model) {
        UserModel user = usersDetailService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/account/change-password";
    }

    /**
     * check two password if it's correct have to change new password
     *
     * @param model
     * @param
     * @param pass1
     * @param pass2
     * @return
     */
    @PostMapping("/update-password")
    public String updatePassword(Model model
            , @RequestParam("newPassword") String pass1
            , @RequestParam("confirmPassword") String pass2,
                                 RedirectAttributes redirectAttributes) {
        String newPassword = pass1;
        String confirmPassword = pass2;
        UserModel currentUser = usersDetailService.getCurrentUser();

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("user", currentUser);
            model.addAttribute("errorPassword", "The password is not match");
            return "user/account/change-password";
        } else {
            UserModel user = service.getCurrentUser();
            userRepository.updatePassword(user.getEmail(), passwordEncoder.encode(confirmPassword));
            redirectAttributes.addFlashAttribute("globalSuccess", "Update Password Successfully");
            return "redirect:/account";
        }

    }

    @GetMapping("/change-email")
    public String changeEmail(Model model) {
        UserModel user = usersDetailService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/account/change-email";
    }

    /**
     * check password of user if it's correct forword to new-email page else return message error
     *
     * @param model
     * @param email
     * @param username
     * @param pass
     * @return
     */
    @PostMapping("/confirm-email")
    public String confirmEmail(Model model,
                               @RequestParam("email") String email,
                               @RequestParam("username") String username,
                               @RequestParam("confirmPassword") String pass) {
        User usr = userRepository.findByUsername(username);
        UserModel user = usersDetailService.getCurrentUser();
        if (passwordEncoder.matches(pass, usr.getPassword())) {
            model.addAttribute("user", user);
            return "user/account/new-email";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("errorInputPassword", "Incorrect password");
            return "user/account/change-email";
        }
    }

    @GetMapping("/new-email")
    public String inputEmail(Model model) {
        UserModel user = usersDetailService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/account/new-email";
    }

    /**
     * update email of current user
     *
     * @param model
     * @param
     * @param newEmail
     * @return
     */
    @PostMapping("/update-email")
    public String updateEmail(Model model,
                              @RequestParam("newEmail") String newEmail,
                              RedirectAttributes redirectAttributes) {
        String user = service.findByEmail(newEmail);
        UserModel currentUser = usersDetailService.getCurrentUser();

        if (user != null) {
            model.addAttribute("user", currentUser);
            model.addAttribute("errorEmail", "This the email already exists.");
            return "user/account/new-email";
        } else {
            UserModel model1 = service.getCurrentUser();
            userRepository.updateEmail(model1.getEmail(), newEmail);
            redirectAttributes.addFlashAttribute("globalSuccess", "Update Email Successfully");
            return "redirect:/account";
        }
    }


    /**
     * update account after login page
     *
     * @param model
     * @param image
     * @param userModel
     * @param result
     * @return
     */
    @PostMapping("edit-account")
    public String updateAccount(Model model,
                                @RequestParam("pic") MultipartFile image,
                                @Valid @ModelAttribute("user") UserModel userModel,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {

        User usr = userRepository.findByUsername(userModel.getUsername());

        if (!image.isEmpty()) { // new image
            String extension = FileUploadUtil.getExtensionName(image).orElse(null);
            String fileName = usr.getAvatar();
            if (fileName == null) {
                fileName = String.valueOf(new Date().getTime()) + '.' + extension;
            } else if (extension != FileUploadUtil.getExtensionName(fileName).orElse(null)) {
                FileUploadUtil.deleteFile("", fileName);
                fileName = fileName.replaceAll("\\w+$", extension);
            }

            FileUploadUtil.saveFile(imgUploadDir, String.valueOf(fileName).replaceAll("\\.\\w+$", ""), image);

            usr.setAvatar(fileName);
        }
        usr.setUsername(usr.getUsername());
        usr.setEmail(userModel.getEmail());
        usr.setPassword(usr.getPassword());
        usr.setPhone(userModel.getPhone());
        usr.setUsername(userModel.getUsername());
        usr.setGender(userModel.getGender());
        usr.setSubName(userModel.getSubName());
        usr.setRoles(usr.getRoles());
        service.save(usr);
        redirectAttributes.addFlashAttribute("globalSuccess", "Update Account Successfully");
        return "redirect:/account";
    }

    @GetMapping("/admin/user")
    public String adminIndex(Model model) {
        List<UserModel> users = userService.findAll();
        model.addAttribute("users", users);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"User", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("User", naviArr));

        return "admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String adminIndex(Model model, @PathVariable("id") int userId) {
        UserModel user = userService.findById(userId);
        model.addAttribute("user", user);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"User", "/admin/user"},
                new String[]{user.getUsername(), ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("User", naviArr));

        return "admin/user-detail";
    }
}
