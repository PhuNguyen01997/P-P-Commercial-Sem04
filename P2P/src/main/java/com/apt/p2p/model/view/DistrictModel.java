package com.apt.p2p.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictModel {
    @JsonProperty("DistrictID")
    public int districtID;
    @JsonProperty("ProvinceID")
    public int provinceID;
    @JsonProperty("DistrictName")
    public String districtName;
    @JsonProperty("NameExtension")
    public String[] nameExtension;
    @JsonProperty("SupportType")
    public int supportType;
}
