package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalShippingForm {
    private int fromDistrictId;
    private int toDistrictId;
    private String toWardCode;
    private double insuranceValue;
}
