package com.apt.p2p.validate;

import com.apt.p2p.model.view.AddressModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressModelValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return AddressModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddressModel address = (AddressModel) target;

//        if(address.getProvince())

//        if(payment.getPostalCode().equals("123")) {
//            errors.rejectValue("postalCode", "example.error.code", "Custom message error validate");
//        }
    }
}
