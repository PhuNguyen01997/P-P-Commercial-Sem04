package com.apt.p2p.model.view;

import groovy.lang.Tuple1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminHeaderNavi {
    private String title;
    private List<String[]> naviItem;
}
