package com.apt.p2p.controller;

import com.apt.p2p.common.FileUploadUtil;
import com.apt.p2p.common.Utility;
import com.apt.p2p.entity.Role;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.RoleRepository;
import com.apt.p2p.repository.UserRepository;
import com.apt.p2p.service.UserService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsersDetailServiceImpl usersDetailService;

    /**
     * call sign in page.
     *
     * @return
     */
    @GetMapping("signin")
    public String signin() {
        return "user/auth/signin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    /**
     * call sign up page.
     *
     * @param model
     * @return
     */
    @GetMapping("signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "user/auth/signup";
    }

    /**
     * save user to DB
     *
     * @param model
     * @param image
     * @param userModel
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/save-user")
    public String save(Model model,
                       @RequestParam("pic") MultipartFile image,
                       @Valid @ModelAttribute("user") UserModel userModel,
                       BindingResult result,
                       final RedirectAttributes attributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("user", userModel);
            return "user/auth/signup";
        }
        Role role = roleRepository.findRoleByName("ROLE_USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        String email = service.findByEmail(userModel.getEmail());

        if (email != null) {
            model.addAttribute("duplicate", "Email already exists");
            return "user/auth/signup";
        }
        User usr = new User();
        if (!image.isEmpty()) {
            String extension = FileUploadUtil.getExtensionName(image).orElse(null);
            String fileName = usr.getAvatar();
            if (fileName == null) {
                fileName =  String.valueOf(new Date().getTime()) + "." + extension;
            } else {
                fileName.replaceAll("\\w+$", extension);
            }
            FileUploadUtil.saveFile("users/", fileName, image);

            usr.setAvatar(fileName);
        } else {
            usr.setAvatar(usr.getAvatar());
        }

        usr.setEmail(userModel.getEmail());
        usr.setPassword(passwordEncoder.encode(userModel.getPassword()));
        usr.setPhone(userModel.getPhone());
        usr.setUsername(userModel.getUsername());
        usr.setRoles(roleList);
        service.save(usr);
        return "redirect:/";

    }

    /**
     * send token to reset page
     *
     * @param model
     * @param token
     * @return
     */
    @GetMapping("reset")
    public String reset(Model model , @Param("token") String token) {
        User user = getUserByToken(token);
        if (user == null) {
            model.addAttribute("errorUser","the time reset your password time out.");
            return "user/auth/error";
        }
        model.addAttribute("token", token);
        return "user/auth/reset";
    }

    /**
     * reset the password
     *
     * @param model
     * @param password1
     * @param password2
     * @return
     */
    @PostMapping("reset-password")
    public String resetPassword( HttpServletRequest request,
                                    Model model,
                                        @RequestParam("newPassword") String password1 ,
                                            @RequestParam("confirmPassword") String password2 ) {
        String token = request.getParameter("token");
        if (password1.isEmpty() || password2.isEmpty()) {
            model.addAttribute("status" , "Passwords is not null.");
            return "user/auth/reset";
        } else {
            if (!password1.equals(password2)) {
                model.addAttribute("status" , "Passwords are not matching, please try again");
                return "user/auth/reset";
            } else {
                User usr = getUserByToken(token);
                if (usr != null) {
                    updatePassword(usr, password2);
                    model.addAttribute("status" , "your password changed success!");
                }
                return "user/auth/signin";
            }
        }
    }

    /**
     * call forgot page.
     *
     * @param model
     * @return
     */
    @GetMapping("forgot")
    public String forgot(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user/auth/forgot";
    }

    /**
     * check exist email and send email to reset the password
     *
     * @param request
     * @param model
     * @param email
     * @return
     */
    @PostMapping("forgot-password")
    public String forgotPassword(HttpServletRequest request,
                                    Model model,
                                        @Valid  @RequestParam("email") String email) {

        if (email.isEmpty()) {
            model.addAttribute("errorEmail", "email is not null");
            return "user/auth/forgot";
        } else {
            String emailUser = service.findByEmail(email);
            if (emailUser != null) {
                model.addAttribute("email", emailUser);
                String token = RandomString.make(50);
                try {
                    userRepository.updateToken(token,emailUser);
                    String resetPasswordLink = Utility.getSiteUrl(request) + "/reset?token=" + token;
                    sendMail(email,resetPasswordLink);
                    model.addAttribute("sendEmail", "we have sent a reset password link to your email. Please check.");

                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("errorEmail", "can't update token");
                }
                return "user/auth/forgot";
            } else {
                model.addAttribute("errorEmail", "Email dose not exists");
                return "user/auth/forgot";
            }
        }
    }

    /**
     * send email to change password
     *
     * @param email
     * @param resetPasswordLink
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private void sendMail(String email , String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("contact@p2p.com" , "P2P Support");
        mimeMessageHelper.setTo(email);
        String subject  = "Here's the link to reset your password";
        String content = "<p>Hello , </p>"
                + "<p> you have requested to reset your password </p>"
                + "<p> click the link below to change your password </p>"
                + "<p><b><a href =\"" + resetPasswordLink + "\">change my password</a></b></p>"
                + "<p> Ignore the email if you do remember your password or you have not made the request </p>";


        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        mailSender.send(mimeMessage);
    }

    /**
     * find user by token
     *
     * @param token
     * @return
     */
    private User getUserByToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    /**
     * update password
     *
     * @param user
     * @param newPassword
     */
    private void updatePassword(User user , String newPassword) {
        String password = passwordEncoder.encode(newPassword);
        user.setPassword(password);
        user.setResetPasswordToken(null);
        service.save(user);
    }



}
