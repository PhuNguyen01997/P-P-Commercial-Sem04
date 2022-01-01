package com.apt.p2p.model.form;

import com.apt.p2p.entity.Province;
import com.apt.p2p.entity.Ward;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictModel {
    private String districtId;

    private String name;

    private ProvinceModel province;

    private List<WardModel> wards;
}
