package com.apt.p2p.validate;

import com.apt.p2p.model.view.CardModel;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CardModelValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return CardModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CardModel card = (CardModel) target;

//        if(card.getPostalCode().equals("123")) {
//            errors.rejectValue("postalCode", "example.error.code", "Custom message error validate");
//        }
    }
}
