package com.apt.p2p.common;

import com.apt.p2p.entity.*;
import com.apt.p2p.model.*;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {
    @Autowired
    @Qualifier("hideCardNumber")
    private Converter hideCardNumberConverter;

    @Autowired
    @Qualifier("removeSpaceNumber")
    private Converter removeSpaceNumber;

    private ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(true)
                .setPropertyCondition(Conditions.isNotNull());
        ;

        return modelMapper;
    }

    public Payment paymentModelToEntity(PaymentModel model) {
        ModelMapper mapper = getModelMapper();
        mapper.typeMap(PaymentModel.class, Payment.class);
        mapper.addMappings(new PropertyMap<PaymentModel, Payment>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getUser());
                using(removeSpaceNumber).map(source.getCvv()).setCvv("error");
                using(removeSpaceNumber).map(source.getNumber()).setNumber("error");
                using(removeSpaceNumber).map(source.getPostalCode()).setPostalCode("error");
            }
        });

        mapper.validate();
        return mapper.map(model, Payment.class);
    }

    public PaymentModel paymentEntityToModel(Payment entity) {
        ModelMapper mapper = getModelMapper();
        mapper.typeMap(Payment.class, PaymentModel.class);
        mapper.addMappings(new PropertyMap<Payment, PaymentModel>() {
            @Override
            protected void configure() {
                skip(destination.getShop());
                skip(destination.getUser());
                using(hideCardNumberConverter).map(source.getNumber()).setNumber("Error mapping");
            }
        });

        mapper.validate();
        return mapper.map(entity, PaymentModel.class);
    }

    public Product productModelToEntity(ProductModel model) {
        ModelMapper mapper = getModelMapper();
        mapper.typeMap(ProductModel.class, Product.class);
        mapper.addMappings(new PropertyMap<ProductModel, Product>() {
            @Override
            protected void configure() {
//                skip(destination.getShop());
//                skip(destination.getUser());
//                using(removeSpaceNumber).map(source.getCvv()).setCvv("error");
//                using(removeSpaceNumber).map(source.getNumber()).setNumber("error");
//                using(removeSpaceNumber).map(source.getPostalCode()).setPostalCode("error");
            }
        });

        mapper.validate();
        return mapper.map(model, Product.class);
    }
}
