package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalShippingForm {
    private Integer[] fromAddressId;
    private Integer toAddressId;
    private BigDecimal insuranceValue;
}
