package com.apt.p2p.controller;

import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("address")
    public String index(Model model) {
        List<AddressModel> addressList = addressService.findByUserId(1);
        model.addAttribute("addressList", addressList);

        return "user/account/address";
    }

    @PostMapping("address")
    public String create() {
        return "redirect/address";
    }

    private List<Integer> calPagiPage(int index, int total) {
        if(index + 1 > total){
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
