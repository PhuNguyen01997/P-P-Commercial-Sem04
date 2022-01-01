package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;

import java.util.List;

public interface LocationService {
    List<Province> provinceFindAll();

    List<District> districtFindAll();

    List<Ward> wardFindAll();

    List<District> districtFindByProvinceId(String provinceId);

    List<Ward> wardFindByDistrictId(String districtId);
}
