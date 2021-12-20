package com.apt.p2p.service;

import com.apt.p2p.entity.Payment;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import com.apt.p2p.repository.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.ValidationException;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private UserRepository userRepository;

    private Payment modelMapEntity(PaymentModel model) {
        try {
            ModelMapper mapper = new ModelMapper();
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
            ModelMapper mapper = new ModelMapper();
            mapper.typeMap(Payment.class, PaymentModel.class);
            mapper.addMappings(mapEntityToModel);
            mapper.validate();
            return mapper.map(entity, PaymentModel.class);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private PropertyMap<PaymentModel, Payment> mapModelToEntity = new PropertyMap<PaymentModel, Payment>() {
        @Override
        protected void configure() {
            skip(destination.getShop());
            skip(destination.getUser());
            using(convertRemoveSpace).map(source.getCvv()).setCvv("error");
            using(convertRemoveSpace).map(source.getNumber()).setNumber("error");
            using(convertRemoveSpace).map(source.getPostalCode()).setPostalCode("error");
        }
    };

    private PropertyMap<Payment, PaymentModel> mapEntityToModel = new PropertyMap<Payment, PaymentModel>() {
        @Override
        protected void configure() {
            skip(destination.getShopId());
            skip(destination.getUserId());
            skip(destination.getShop());
            skip(destination.getUser());
            using(convertAddSpaceNumber).map(source.getNumber()).setNumber("Error mapping");
        }
    };

    private Converter<String, String> convertRemoveSpace = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> mappingContext) {
            return mappingContext.getSource().replaceAll("\\s+", "");
        }
    };

    private Converter<String, String> convertAddSpaceNumber = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> mappingContext) {
            StringBuilder builder = new StringBuilder(mappingContext.getSource());
            int mark = builder.length() - 4;
            for (int i = 0; i < mark; i++) {
                builder.replace(i, i + 1, "*");
            }
            for (int i = 4; i < builder.length(); i += 5) {
                builder.insert(i, " ");
            }

            return builder.toString();
        }
    };

    @Override
    public PaymentModel create(PaymentModel paymentModel) {
        try {
            Payment payment = modelMapEntity(paymentModel);
            payment.setUser(userRepository.findById(1).get());
            repository.save(payment);
            return entityMapModel(payment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PaymentModel> findAll() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Payment.class, PaymentModel.class);
        mapper.addMappings(mapEntityToModel);
        mapper.validate();

        return repository.findAll().stream().map(pe -> mapper.map(pe, PaymentModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentModel> findAllByUserId(int userId) {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Payment.class, PaymentModel.class);
        mapper.addMappings(mapEntityToModel);
        mapper.validate();

        Iterable<Payment> test = repository.findAllByUserId(userId);
        List<PaymentModel> result = repository.findAllByUserId(userId).stream().map(pe -> mapper.map(pe, PaymentModel.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean delete(int id) {
        try {
            Payment payment = repository.findById(id).get();
            if(payment.getShop() != null){
                payment.setUser(null);
                repository.save(payment);
            }else{
                repository.deleteById(id);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            return true;
        }
    }
}
