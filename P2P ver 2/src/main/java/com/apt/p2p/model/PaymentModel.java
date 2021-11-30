package com.apt.p2p.model;

import java.util.Date;

public class PaymentModel {
    private Integer id;

    private String fullname;

    private String number;

    private  Integer type;

    private Date due;

    private Integer cvv;

    private String addressRegister;

    private Integer postalCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getAddressRegister() {
        return addressRegister;
    }

    public void setAddressRegister(String addressRegister) {
        this.addressRegister = addressRegister;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }
}
