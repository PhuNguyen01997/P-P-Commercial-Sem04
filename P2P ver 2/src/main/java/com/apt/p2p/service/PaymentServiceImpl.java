package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import com.apt.p2p.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper mapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository, UserRepository userRepository, ModelMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public Payment create(PaymentModel paymentModel) {
        Payment payment = modelMapEntity(paymentModel);
        return payment;
    }

    private Payment modelMapEntity(PaymentModel model){
        try {
            mapper.createTypeMap(PaymentModel.class, Payment.class);
            mapper.validate();
            mapper.addMappings(mapper -> {
                mapper.map(src -> src.get)
            })
            return mapper.map(model, Payment.class);
        } catch(ValidationException e){
            throw e;
        }
    }

    private PropertyMap<PaymentModel, Payment> mapModelToEntity = new PropertyMap<PaymentModel, Payment>() {
        @Override
        protected void configure() {
            Optional<User> userEntity = userRepository.findById(source.getUserId());
            map().setUser(userEntity.isPresent() ? userEntity.get() : null);
        }
    }
}
