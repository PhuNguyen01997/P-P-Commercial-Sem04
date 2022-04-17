package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterRatePortal {
    private Integer shopId;
    private String productName;
    private String userName;
    private Date minDate;
    private Date maxDate;
    private Integer star;
}
