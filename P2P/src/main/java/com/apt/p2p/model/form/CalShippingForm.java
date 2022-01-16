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
    private Integer[] fromAddressId;
    private Integer toAddressId;
    private double insuranceValue;
}
