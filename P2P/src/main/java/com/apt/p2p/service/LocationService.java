package com.apt.p2p.service;

import com.apt.p2p.entity.District;
import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.apt.p2p.model.form.CalShippingForm;
import com.apt.p2p.model.form.CalShippingResponseData;
import com.apt.p2p.model.view.DistrictModel;
import com.apt.p2p.model.view.ProvinceModel;
import com.apt.p2p.model.view.WardModel;

import java.math.BigDecimal;
import java.util.List;

public interface LocationService {
    List<ProvinceModel> provinceFindAll();

    List<DistrictModel> districtFindAllByProvinceId(int id);

    List<WardModel> wardFindAllByDistrictId(int districtId);

    List<BigDecimal> calShippingFree(CalShippingForm form);
}
