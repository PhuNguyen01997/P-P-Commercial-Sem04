package com.apt.p2p.controller;

import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.service.AddressService;
import com.apt.p2p.validate.AddressModelValidator;
import com.apt.p2p.validate.PaymentModelValidator;
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
        List<AddressModel> addressList = addressService.findAllByUserId(1);
        model.addAttribute("addressList", addressList);
        model.addAttribute("addressForm", new AddressModel());

        return "user/account/address";
    }

    @PostMapping("address")
    public String create(Model model,
                         @Valid @ModelAttribute("addressForm") AddressModel address,
                         BindingResult result) {
        if (result.hasErrors()) {
            List<AddressModel> addressList = addressService.findAllByUserId(1);
            model.addAttribute("addressList", addressList);
            model.addAttribute("addressForm", address);
            model.addAttribute("hasAnyError", true);

            return "user/account/address";
        }

        AddressModel addressModel = addressService.save(address);

        return "redirect:/address";
    }

    @PostMapping("address/{id}")
    public String edit(Model model,
                       @Valid @ModelAttribute("addressForm") AddressModel address,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            List<AddressModel> addressList = addressService.findAllByUserId(1);
            model.addAttribute("addressList", addressList);
            model.addAttribute("addressForm", address);
            model.addAttribute("hasAnyError", true);

            return "user/account/address";
        }

        AddressModel success = addressService.update(address);
        if(success == null){
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra khi cập nhật, xin hãy thử lại sau");
        }
        return "redirect:/address";
    }

    @DeleteMapping("address/{id}")
    public String delete(@PathVariable("id") int id,
                         RedirectAttributes redirectAttributes) {
        boolean success = addressService.delete(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("globalError", "Có lỗi xãy ra không thể xóa, xin hãy thử lại sau");
        }
        return "redirect:/address";
    }

    @GetMapping("/calShipping/{shopAddressId}/{addressId}")
    @ResponseBody
    public String calShipping(@PathVariable("shopAddressId") int shopAddressId, @PathVariable("addressId") int addressId){
        return null;
    }

    private List<Integer> calPagiPage(int index, int total) {
        if (index + 1 > total) {
            throw new IllegalArgumentException("Index is > Pagi length");
        }
        List<Integer> result = new ArrayList<>();
        int showRange = 2;
        int borderStart = showRange;
        int borderEnd = total - showRange - 1;
        if (borderStart > borderEnd) {
            for (int i = 0; i <= total - 1; i++) {
                result.add(i);
            }
        } else if (borderStart <= index && index <= borderEnd) {
            for (int i = index - showRange; i < index; i++) {
                result.add(i);
            }
            for (int i = index; i <= index + showRange; i++) {
                result.add(i);
            }
        } else if (index >= borderEnd) {
            for (int i = total - 1; i > total - 1 - ((showRange * 2) + 1); i--) {
                result.add(0, i);
            }
        } else if (index <= borderStart) {
            for (int i = 0; i < ((showRange * 2) + 1); i++) {
                result.add(i);
            }
        }
        return result;
    }
}
