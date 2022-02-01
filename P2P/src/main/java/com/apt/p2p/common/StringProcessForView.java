package com.apt.p2p.common;

import com.apt.p2p.entity.CardType;
import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.UserModel;

import java.math.BigDecimal;

public class StringProcessForView {
    public static String getFullAddress(AddressModel addressModel) {
        StringBuilder stringBuilder = new StringBuilder(addressModel.getNumber());
        stringBuilder.append(", " + addressModel.getWard());
        stringBuilder.append(", " + addressModel.getDistrict());
        stringBuilder.append(", " + addressModel.getProvince());
        return stringBuilder.toString();
    }

    public static String getImgUrlByType(String type){
        String url = "";
        switch (type.toUpperCase()) {
            case "VISA": {
                url = "/img/common/icon-visa.png";
                break;
            }
            case "MASTERCARD": {
                url = "/img/common/icon-master-card.png";
                break;
            }
        }
        return url;
    }

    public static String getTotalOrder(OrderModel orderModel){
        BigDecimal total = orderModel.getOrderDetails().stream().map(OrderDetailModel::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.toString();
    }
}
