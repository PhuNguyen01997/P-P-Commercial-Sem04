package com.apt.p2p.entity;

public enum CardType {
    VISA("Visa"), MASTER_CARD("Master card");

    private final String displayValue;

    private CardType(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return this.displayValue;
    }
}
