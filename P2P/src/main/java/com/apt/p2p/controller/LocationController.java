package com.apt.p2p.controller;

import com.apt.p2p.common.modelMapper.DistrictMapper;
import com.apt.p2p.common.modelMapper.ProvinceMapper;
import com.apt.p2p.common.modelMapper.WardMapper;
import com.apt.p2p.model.form.DistrictModel;
import com.apt.p2p.model.form.WardModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private WardMapper wardMapper;

    @GetMapping("/provinces")
    @ResponseBody
    public List<ProvinceModel> getProvinces(){
        return locationService.provinceFindAll();
//                .stream().map(pe -> provinceMapper.provinceEntityToModel(pe))
//                .collect(Collectors.toList());
    }

    @GetMapping("/districts")
    @ResponseBody
    public List<DistrictModel> getDistricts(){
        return locationService.districtFindAll()
                .stream().map(de -> districtMapper.districtEntityToModel(de))
                .collect(Collectors.toList());
    }

    @GetMapping("/wards")
    @ResponseBody
    public List<WardModel> getWards(){
        return locationService.wardFindAll()
                .stream().map(pe -> wardMapper.wardEntityToModel(pe))
                .collect(Collectors.toList());
    }

    @GetMapping("/{province}/districts")
    @ResponseBody
    public List<DistrictModel> getDistrictByProvince(@PathVariable("province") String provinceId){
        return locationService.districtFindByProvinceId(provinceId)
                .stream().map(pe -> districtMapper.districtEntityToModel(pe))
                .collect(Collectors.toList());
    }

    @GetMapping("/{district}/wards")
    @ResponseBody
    public List<WardModel> getWardByProvince(@PathVariable("district") String districtId){
        return locationService.wardFindByDistrictId(districtId)
                .stream().map(pe -> wardMapper.wardEntityToModel(pe))
                .collect(Collectors.toList());
    }
}
