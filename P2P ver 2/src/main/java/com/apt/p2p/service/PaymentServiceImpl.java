package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Payment create(PaymentModel paymentModel) {
        Payment payment = modelMapEntity(paymentModel);
        return payment;
    }

    public Payment modelMapEntity(PaymentModel model){
        try {
            mapper.createTypeMap(PaymentModel.class, Payment.class);
            mapper.validate();
            return mapper.map(model, Payment.class);
        } catch(ValidationException e){
            throw e;
        }
    }
}
