package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalShippingResponseData {
    public double total;
    public double service_fee;
    public double insurance_fee;
    public double pick_station_fee;
    public double coupon_value;
    public double r2s_fe;
}
