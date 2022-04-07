package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagiSortModel {
    private String sortBy;
    private Boolean sortDirection = true;
    private Integer page = 0;
    private Integer size;
}
