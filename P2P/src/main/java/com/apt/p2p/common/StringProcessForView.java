package com.apt.p2p.common;

import com.apt.p2p.model.view.AddressModel;

public class StringProcessForView {
    public static String getFullAddress(AddressModel addressModel){
        StringBuilder stringBuilder = new StringBuilder(addressModel.getNumber());
        stringBuilder.append(", " + addressModel.getWard());
        stringBuilder.append(", " + addressModel.getDistrict());
        stringBuilder.append(", " + addressModel.getProvince());
        return stringBuilder.toString();
    }
}
