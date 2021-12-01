package com.apt.p2p.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentModel {
    private Integer id;

    private String fullname;

    private String number;

    private  Integer type;

    private Date due;

    private Integer cvv;

    private String addressRegister;

    private Integer postalCode;

    public PaymentModel() {
    }
}
