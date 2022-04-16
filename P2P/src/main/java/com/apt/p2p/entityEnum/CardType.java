package com.apt.p2p.entityEnum;

public enum CardType {
    VISA("Visa"), MASTER_CARD("MasterCard");

    private final String displayValue;

    CardType(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return this.displayValue;
    }
}
