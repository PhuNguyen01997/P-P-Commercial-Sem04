package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.RateMapper;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.model.form.FilterRatePortal;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.form.RateMultiProductForm;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ResponsePagiView;
import com.apt.p2p.model.view.ResponseRateApiPagi;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.service.OrderService;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.ShopService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RateController {
    @Autowired
    private UsersDetailServiceImpl userService;
    @Autowired
    private RateService rateService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private RateMapper rateMapper;

    @PostMapping ("/api/products/{productId}/rate")
    @ResponseBody
    public ResponseRateApiPagi apiGetRateByProduct(@PathVariable("productId") int productId, @RequestBody PagiSortModel pagiSortModel){
        Page<Rate> pageRates = rateService.findAllByProductId(productId, pagiSortModel);

        List<RateModel> rates = pageRates.stream().map(e -> rateMapper.rateEntityToModel(e)).collect(Collectors.toList());

        ResponseRateApiPagi result = new ResponseRateApiPagi(pagiSortModel.getPage(), pageRates.getTotalPages(), rates);

        return result;
    }

    @PostMapping("/rate/{orderId}")
    public String rateProduct(@ModelAttribute RateMultiProductForm ratesForm, @PathVariable("orderId") int orderId){
        UserModel user = userService.getCurrentUser();
        for (int i = 0; i < ratesForm.getRates().size(); i++) {
            rateService.create(user.getId(), ratesForm.getProductId().get(i), ratesForm.getRates().get(i));
        }

        orderService.updateStatus(orderId, 5);
        return "redirect:/order/" + orderId;
    }

    @GetMapping("portal/rate")
    public String portalRate(Model model){
        UserModel user = userService.getCurrentUser();
        model.addAttribute("user", user);

        model.addAttribute("shop", user.getShop() != null ? shopService.findByUserId(user.getId()) : null);

        return "user/portal/rate";
    }

    @PostMapping("api/rate/portal")
    @ResponseBody
    public List<RateModel> apiGetRateByFilter(@RequestBody FilterRatePortal filterRatePortal){
        return rateService.findAllByFilterPortal(filterRatePortal);
    }
}
