package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.stripe.StripeCustomer;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {
    private String customerUrl = "https://api.stripe.com/v1/customers";

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkout() {
        User user = userRepository.findById(3).get();
        StripeCustomer stripeCustomer = findCustomer(user.getStripeCustomerId());
        return false;
    }

    @Override
    public StripeCustomer createCustomer() {
        return null;
    }


    public StripeCustomer findCustomer(String customerId){
        
    }
}
