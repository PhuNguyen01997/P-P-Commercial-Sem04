package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {
    private String customerUrl = "https://api.stripe.com/v1/customers";
    @Value("${stripe.secrect.key}")
    private String stripeApiKey;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkout() {
        User user = userRepository.findById(3).get();
        Customer customer = findCustomer(user.getStripeCustomerId());
        if(customer == null){
            customer = createCustomer(user.getId());
        }
        if(customer == null){
            return false;
        }
        return true;
    }

    @Override
    public Customer createCustomer(int userId) {
        User user = userRepository.findById(userId).get();
        Stripe.apiKey = stripeApiKey;
        Customer customer = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("email", user.getEmail());
            params.put("name", user.getUsername());
            params.put("phone", user.getPhone());
            customer = Customer.create(params);
            user.setStripeCustomerId(customer.getId());
            userRepository.save(user);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return customer;
    }


    public Customer findCustomer(String customerId) {
        Customer customer = null;
        try {
            Stripe.apiKey = stripeApiKey;
            customer = Customer.retrieve(customerId);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
