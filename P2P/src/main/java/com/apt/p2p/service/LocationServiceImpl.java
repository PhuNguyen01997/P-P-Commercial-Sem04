package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.repository.DistrictRepository;
import com.apt.p2p.repository.ProvinceRepository;
import com.apt.p2p.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Province> provinceFindAll() {
        return provinceRepository.findAllByOrderByName();
    }

    @Override
    public List<District> districtFindAll() {
        return districtRepository.findAllByOrderByName();
    }

    @Override
    public List<Ward> wardFindAll() {
        return wardRepository.findAllByOrderByName();
    }

    @Override
    public List<District> districtFindByProvinceId(String provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);
    }

    @Override
    public List<Ward> wardFindByDistrictId(String districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }
}
