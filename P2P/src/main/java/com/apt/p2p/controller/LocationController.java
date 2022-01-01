package com.apt.p2p.controller;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/provinces")
    public List<Province> getProvinces(){
        return locationService.provinceFindAll();
    }

    @GetMapping("/districts")
    public List<District> getDistricts(){
        return locationService.districtFindAll();
    }

    @GetMapping("/wards")
    public List<Ward> getWards(){
        return locationService.wardFindAll();
    }

    @GetMapping("/{province}")
    public List<District> getDistrictByProvince(@PathVariable("province") String provinceId){
        return locationService.districtFindByProvinceId(provinceId);
    }

    @GetMapping("/{province}/{district}")
    public List<Ward> getWardByProvince(@PathVariable("district") String districtId){
        return locationService.wardFindByDistrictId(districtId);
    }
}
