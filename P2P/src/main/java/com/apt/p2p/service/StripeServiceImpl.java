package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;
import com.stripe.model.PaymentSourceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {
    private String customerUrl = "https://api.stripe.com/v1/customers";
    @Value("${stripe.secret.key}")
    private String stripeApiKey;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public Customer getCustomer(int userId, boolean withSource) {
        User user = userRepository.findById(userId).get();
        String customerId = user.getStripeCustomerId();

        if (customerId == null) {
            return null;
        }

        Customer customer = null;
        try {
            if (withSource) {
                List<String> expandList = new ArrayList<>();
                expandList.add("sources");

                Map<String, Object> retrieveParams = new HashMap<>();
                retrieveParams.put("expand", expandList);
                customer = Customer.retrieve(customerId, retrieveParams, null);
            } else {
                customer = Customer.retrieve(customerId);
            }
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Card> getCards(int userId) {
        List<Card> result = new ArrayList<>();
        Customer customer = getCustomer(userId, true);

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("object", "card");

            PaymentSourceCollection cards =
                    customer.getSources().list(params);
            List<PaymentSource> listPaymentSource = cards.getData();
            for (PaymentSource card : listPaymentSource) {
                result.add((Card) card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


//    @Override
//    public boolean checkout() {
//        User user = userRepository.findById(3).get();
//        Customer customer = findCustomer(user.getStripeCustomerId());
//        if(customer == null){
//            customer = createCustomer(user.getId());
//        }
//        if(customer == null){
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public Customer createCustomer(int userId) {
//        User user = userRepository.findById(userId).get();
//        Stripe.apiKey = stripeApiKey;
//        Customer customer = null;
//        try {
//            Map<String, Object> params = new HashMap<>();
//            params.put("email", user.getEmail());
//            params.put("name", user.getUsername());
//            params.put("phone", user.getPhone());
//            customer = Customer.create(params);
//            user.setStripeCustomerId(customer.getId());
//            userRepository.save(user);
//        } catch (StripeException e) {
//            e.printStackTrace();
//        }
//        return customer;
//    }
//
//
//    public Customer findCustomer(String customerId) {
//        Customer customer = null;
//        try {
//            Stripe.apiKey = stripeApiKey;
//            customer = Customer.retrieve(customerId);
//        } catch (StripeException e) {
//            e.printStackTrace();
//        }
//        return customer;
//    }
}
