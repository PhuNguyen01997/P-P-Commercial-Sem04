package com.apt.p2p.controller;

import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.model.form.FilterShopTransaction;
import com.apt.p2p.model.view.AdminHeaderNavi;
import com.apt.p2p.model.view.ShopTransactionModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ShopTransactionController {
    @Autowired
    private ShopTransactionService shopTransactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/transaction")
    public String adminIndex(Model model){
        List<ShopTransactionModel> transactions = shopTransactionService.findAll();

        model.addAttribute("transactions", transactions);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Giao dịch", ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Giao dịch", naviArr));

        return "/admin/transaction";
    }

    @GetMapping("/admin/transaction/{id}")
    public String adminDetail(Model model, @PathVariable("id") int id){
        ShopTransactionModel shopTransactionModel = shopTransactionService.findById(id);
        shopTransactionModel.setShop(shopService.findById(shopTransactionModel.getShop().getId()));
        if(shopTransactionModel.getOrder() != null){
            shopTransactionModel.setOrder(orderService.findById(shopTransactionModel.getOrder().getId()));
        }

        model.addAttribute("transaction", shopTransactionModel);

        List<String[]> naviArr = Arrays.asList(
                new String[]{"Home", "/admin"},
                new String[]{"Giao dịch", "/admin/shop"},
                new String[]{String.valueOf(shopTransactionModel.getId()), ""}
        );
        model.addAttribute("viewHeaderNavi", new AdminHeaderNavi("Giao dịch", naviArr));

        return "admin/transaction-detail";
    }

    @PostMapping("/api/shop/{id}/fund")
    @ResponseBody
    public List<ShopTransactionModel> apiIndex(@PathVariable("id") int shopId, @RequestBody FilterShopTransaction input) {
        LocalDateTime ldtMaxDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(input.getMaxDate().getTime()), ZoneOffset.UTC);
        ldtMaxDate = ldtMaxDate.plusDays(1);
        Date dMaxDate = Date.from(ldtMaxDate.toInstant(ZoneOffset.UTC));
        input.setMaxDate(dMaxDate);

        List<ShopTransactionModel> result = shopTransactionService.findAllByShopIdWithFilter(shopId, input);

        return result;
    }
}
