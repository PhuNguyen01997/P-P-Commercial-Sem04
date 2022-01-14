package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.view.ProvinceModel;

import java.util.List;

public interface LocationService {
    List<ProvinceModel> provinceFindAll();

    List<District> districtFindAll();

    List<Ward> wardFindAll();

    List<District> districtFindByProvinceId(String provinceId);

    List<Ward> wardFindByDistrictId(String districtId);
}
