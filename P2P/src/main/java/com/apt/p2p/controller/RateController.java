package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.RateMapper;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.model.view.ResponsePagiView;
import com.apt.p2p.model.view.ResponseRateApiPagi;
import com.apt.p2p.service.RateService;
import com.apt.p2p.service.UsersDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
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
    private RateMapper rateMapper;

    @PostMapping ("/api/products/{productId}/rate")
    @ResponseBody
    public ResponseRateApiPagi apiGetRateByProduct(@PathVariable("productId") int productId, @RequestBody PagiSortModel pagiSortModel){
        Page<Rate> pageRates = rateService.findAllByProductId(productId, pagiSortModel);

        List<RateModel> rates = pageRates.stream().map(e -> rateMapper.rateEntityToModel(e)).collect(Collectors.toList());

        ResponseRateApiPagi result = new ResponseRateApiPagi(pagiSortModel.getPage(), pageRates.getTotalPages(), rates);

        return result;
    }

    @PostMapping("/rate")
    public String rateProduct(@ModelAttribute("rate") RateModel[] ratesForm){
        return "asd";
    }
}
