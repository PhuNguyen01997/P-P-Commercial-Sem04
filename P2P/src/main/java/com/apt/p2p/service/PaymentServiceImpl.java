package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import com.apt.p2p.repository.ShopRepository;
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
    public PaymentServiceImpl(PaymentRepository repository, UserRepository userRepository, ShopRepository shopRepository, ModelMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentModel create(PaymentModel paymentModel) {
        try {
            Payment payment = modelMapEntity(paymentModel);
            payment.setUser(userRepository.findById(1).get());
            repository.save(payment);
            return entityMapModel(payment);
        } catch (Exception e) {
            return null;
        }
    }

    private Payment modelMapEntity(PaymentModel model) {
        try {
            mapper.typeMap(PaymentModel.class, Payment.class);
            mapper.addMappings(mapModelToEntity);
            mapper.validate();
            return mapper.map(model, Payment.class);
        } catch (ValidationException e) {
            throw e;
        }
    }

    private PaymentModel entityMapModel(Payment entity) {
        try {
            mapper.typeMap(Payment.class, PaymentModel.class);
            mapper.addMappings(mapEntityToModel);
            mapper.validate();
            return mapper.map(entity, PaymentModel.class);
        } catch (ValidationException e) {
            throw e;
        }
    }

    private PropertyMap<PaymentModel, Payment> mapModelToEntity = new PropertyMap<PaymentModel, Payment>() {
        @Override
        protected void configure() {
            skip(destination.getShop());
            skip(destination.getUser());
//            Optional<User> userEntity = userRepository.findById(source.getUserId());
//            User entity = userEntity.get();
//            map().setUser(userEntity.isPresent() ? userEntity.get() : null);
        }
    };

    private PropertyMap<Payment, PaymentModel> mapEntityToModel = new PropertyMap<Payment, PaymentModel>() {
        @Override
        protected void configure() {
            skip(destination.getShopId());
//            destination.setUserId(source.getUser());
        }
    };
}
