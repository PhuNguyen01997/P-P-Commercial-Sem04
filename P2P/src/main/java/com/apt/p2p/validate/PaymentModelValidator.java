package com.apt.p2p.validate;

import com.apt.p2p.model.view.PaymentModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PaymentModelValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return PaymentModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PaymentModel payment = (PaymentModel) target;

//        if(payment.getPostalCode().equals("123")) {
//            errors.rejectValue("postalCode", "example.error.code", "Custom message error validate");
//        }
    }
}
