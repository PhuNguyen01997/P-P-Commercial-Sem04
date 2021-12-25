package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.PaymentMapper;
import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.PaymentModel;
import com.apt.p2p.repository.PaymentRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public PaymentModel create(PaymentModel paymentModel) {
        try {
            Payment payment = paymentMapper.paymentModelToEntity(paymentModel);
            payment.setUser(userRepository.findById(1).get());
            repository.save(payment);
            return paymentMapper.paymentEntityToModel(payment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PaymentModel> findAll() {
        return repository.findAll().stream().map(pe -> paymentMapper.paymentEntityToModel(pe)).collect(Collectors.toList());
    }

    @Override
    public List<PaymentModel> findAllByUserId(int userId) {
        Iterable<Payment> test = repository.findAllByUserId(userId);
        List<PaymentModel> result = repository.findAllByUserId(userId).stream().map(pe -> paymentMapper.paymentEntityToModel(pe)).collect(Collectors.toList());
        return result;
    }

    @Override
    public boolean delete(int id) {
        try {
            Payment payment = repository.findById(id).get();
            if (payment.getShop() != null) {
                payment.setUser(null);
                repository.save(payment);
            } else {
                repository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            return true;
        }
    }
}
