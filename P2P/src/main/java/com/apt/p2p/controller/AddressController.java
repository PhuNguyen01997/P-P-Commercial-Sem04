package com.apt.p2p.controller;

import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.AddressService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import com.apt.p2p.validate.AddressModelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private AddressService addressService;

    @InitBinder("addressForm")
    protected void initBinder(WebDataBinder binder) {
        // trim at start and end String
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);

        binder.addValidators(new AddressModelValidator());
    }

    @GetMapping("address")
    public String index(Model model) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        List<AddressModel> addressList = addressService.findAllByUserId(user.getId());
        model.addAttribute("addressList", addressList);
        model.addAttribute("addressForm", new AddressModel());

        return "user/account/address";
    }

    @PostMapping("address")
    public String create(Model model,
                         @Valid @ModelAttribute("addressForm") AddressModel address,
                         BindingResult result) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            List<AddressModel> addressList = addressService.findAllByUserId(user.getId());
            model.addAttribute("addressList", addressList);
            model.addAttribute("addressForm", address);
            model.addAttribute("hasAnyError", true);

            return "user/account/address";
        }

        AddressModel addressModel = addressService.save(user.getId(), address);

        return "redirect:/address";
    }

    @PostMapping("address/{id}")
    public String edit(Model model,
                       @Valid @ModelAttribute("addressForm") AddressModel address,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            List<AddressModel> addressList = addressService.findAllByUserId(user.getId());
            model.addAttribute("addressList", addressList);
            model.addAttribute("addressForm", address);
            model.addAttribute("hasAnyError", true);

            return "user/account/address";
        }

        AddressModel success = addressService.update(address);
        if(success == null){
            redirectAttributes.addFlashAttribute("globalError", "An error occurred while updating, please try again late");
        }
        return "redirect:/address";
    }

    @DeleteMapping("address/{id}")
    public String delete(@PathVariable("id") int id,
                         RedirectAttributes redirectAttributes) {
        boolean success = addressService.delete(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("globalError", "An error occurred that cannot be cleared, please try again later");
        }
        return "redirect:/address";
    }
}
