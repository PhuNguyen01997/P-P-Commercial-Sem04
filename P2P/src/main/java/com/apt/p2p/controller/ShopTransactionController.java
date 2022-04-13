package com.apt.p2p.controller;

import com.apt.p2p.model.form.FilterShopTransaction;
import com.apt.p2p.model.view.ShopTransactionModel;
import com.apt.p2p.service.ShopTransactionService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Controller
public class ShopTransactionController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private ShopTransactionService shopTransactionService;

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
