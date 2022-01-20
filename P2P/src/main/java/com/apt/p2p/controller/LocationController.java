package com.apt.p2p.controller;

import com.apt.p2p.model.form.CalShippingForm;
import com.apt.p2p.model.form.CalShippingResponseData;
import com.apt.p2p.model.view.DistrictModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.WardModel;
import com.apt.p2p.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/provinces")
    @ResponseBody
    public List<ProvinceModel> getProvinces(){
        return locationService.provinceFindAll();
    }

    @GetMapping("/{province}/districts")
    @ResponseBody
    public List<DistrictModel> getDistrictByProvince(@PathVariable("province") String provinceId){
        return locationService.districtFindAllByProvinceId(Integer.parseInt(provinceId));
    }

    @GetMapping("/{district}/wards")
    @ResponseBody
    public List<WardModel> getWardByProvince(@PathVariable("district") String districtId){
        return locationService.wardFindAllByDistrictId(Integer.parseInt(districtId));
    }

    @PostMapping("/calFee")
    @ResponseBody
    public double calFee(@RequestBody CalShippingForm calForm){
        CalShippingResponseData infoFee = locationService.calShippingFree(calForm);
        return infoFee.getTotal();
    }
}
