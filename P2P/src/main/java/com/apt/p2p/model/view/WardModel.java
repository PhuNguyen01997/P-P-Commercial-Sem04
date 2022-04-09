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
public class WardModel {
    @JsonProperty("WardCode")
    public String wardCode;
    @JsonProperty("DistrictID")
    public int districtId;
    @JsonProperty("WardName")
    public String wardName;
    @JsonProperty("NameExtension")
    public String[] nameExtension;
    @JsonProperty("SupportType")
    public int supportType;
}
