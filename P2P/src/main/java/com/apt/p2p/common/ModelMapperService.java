package com.apt.p2p.common;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {
    @Autowired
    private Converter hideCardNumberConverter;

    private ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setSkipNullEnabled(true);

        return modelMapper;
    }

    public Payment paymentModelToEntity(PaymentModel model){
        ModelMapper mapper = getModelMapper();
        mapper.typeMap(PaymentModel.class, Payment.class);
        mapper.addMappings(new PropertyMap<PaymentModel, Payment>() {
            @Override
            protected void configure() {

            }
        });
        mapper.validate();
        return mapper.map(model, Payment.class);
    }

    public PaymentModel paymentEntityToModel(Payment entity){
        ModelMapper mapper = getModelMapper();
        mapper.typeMap(Payment.class, PaymentModel.class);
        mapper.addMappings(new PropertyMap<Payment, PaymentModel>() {
            @Override
            protected void configure() {
                using(hideCardNumberConverter).map(source.getNumber()).setNumber("Error mapping");
            }
        });

        mapper.validate();
        return mapper.map(entity, PaymentModel.class);
    }
}
