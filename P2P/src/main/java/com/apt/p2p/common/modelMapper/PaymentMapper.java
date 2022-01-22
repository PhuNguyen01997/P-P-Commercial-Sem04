package com.apt.p2p.common.modelMapper;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.view.PaymentModel;
import com.stripe.model.Card;
import com.stripe.model.PaymentSource;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.Date;

@Service
public class PaymentMapper {
    @Autowired
    private MapperService mapperService;

//    @Autowired
//    @Qualifier("hideCardNumber")
//    private Converter hideCardNumberConverter;

    @Autowired
    @Qualifier("removeSpaceNumber")
    private Converter removeSpaceNumber;

    @Autowired
    @Qualifier("setImageCard")
    private Converter setImageCard;

//    public Payment paymentModelToEntity(PaymentModel model) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(PaymentModel.class, Payment.class);
//        mapper.addMappings(new PropertyMap<PaymentModel, Payment>() {
//            @Override
//            protected void configure() {
//                skip(destination.getShop());
//                skip(destination.getUser());
//                skip(destination.getOrders());
//                using(removeSpaceNumber).map(source.getPostalCode()).setPostalCode("error");
//                using(removeSpaceNumber).map(source.getCvv()).setCvv("error");
//                using(removeSpaceNumber).map(source.getNumber()).setNumber("error");
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(model, Payment.class);
//    }

//    public PaymentModel paymentEntityToModel(Payment entity) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(Payment.class, PaymentModel.class);
//        mapper.addMappings(new PropertyMap<Payment, PaymentModel>() {
//            @Override
//            protected void configure() {
//                skip(destination.getShop());
//                skip(destination.getUser());
//                skip(destination.getOrders());
//                using(setImageCard).map(source.getType(), destination.getImgUrl());
//                using(hideCardNumberConverter).map(source.getNumber()).setNumber("Error mapping");
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(entity, PaymentModel.class);
//    }

    public PaymentModel stripeCardToPaymentModel(Card source) {
        ModelMapper mapper = mapperService.getModelMapper();

        try {
            mapper.typeMap(Card.class, PaymentModel.class);
//            mapper.addMappings(new PropertyMap<Card, PaymentModel>() {
//                @Override
//                protected void configure() {
//                    skip(destination.getShop());
//                    skip(destination.getUser());
//                    skip(destination.getOrders());
////                map().setFullname(source.getName());
////                map().setAddressRegister(source.getAddressCountry());
////                map().setPostalCode(source.getAddressZip());
////                map().setDue(new Date(source.getExpYear().intValue(), source.getExpMonth().intValue(), 1));
////                map().setType(source.getBrand());
//
////                using(setImageCard).map(source.getType(), destination.getImgUrl());
////                using(hideCardNumberConverter).map(source.getNumber()).setNumber("Error mapping");
//                }
//            });
        } catch (Exception e){
            e.printStackTrace();
        }

        mapper.validate();
        return mapper.map(source, PaymentModel.class);
    }
}
