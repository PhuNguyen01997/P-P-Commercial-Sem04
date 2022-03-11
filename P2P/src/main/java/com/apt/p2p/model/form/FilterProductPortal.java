package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterProductPortal {
    private int filterBy;

    private String name;

    private int categoryId;

    private int status;
}
