package com.apt.p2p.model.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceModel implements Serializable {
    @JsonProperty("ProvinceID")
    public int provinceId;
    @JsonProperty("ProvinceName")
    public String provinceName;
    @JsonProperty("CountryID")
    public int countryID;
    @JsonProperty("NameExtension")
    public String[] nameExtension;
    @JsonProperty("RegionID")
    public int regionID;
    @JsonProperty("SupportType")
    public int supportType;
}
