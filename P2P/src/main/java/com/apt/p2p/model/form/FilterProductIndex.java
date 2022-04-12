package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterProductIndex {
    private String keyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer rate;
    private List<Integer> provinceId = new ArrayList<>();
}
