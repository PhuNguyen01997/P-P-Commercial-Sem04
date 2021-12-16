package com.apt.p2p.validate;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
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

        if(payment.getPostalCode() == 123) {
            errors.rejectValue("postalCode", "postalCode not valid");
        }
        // do "complex" validation here

    }
}
