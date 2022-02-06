package com.apt.p2p.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterOrder {
    private int statusId;

    private int filterBy;

    private String name;

    private Date minDate;

    private Date maxDate;
}
