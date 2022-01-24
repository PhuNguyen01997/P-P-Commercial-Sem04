package com.apt.p2p.service;

import com.apt.p2p.entity.User;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.PaymentModel;
import com.apt.p2p.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public Customer createCustomer(int userId) throws StripeException {
        User user = userRepository.findById(userId).get();

        Customer customer = null;
        Map<String, Object> createParams = new HashMap<>();
        createParams.put("email", user.getEmail());
        createParams.put("name", user.getUsername());
        createParams.put("phone", user.getPhone());

        customer = Customer.create(createParams);

        user.setStripeCustomerId(customer.getId());
        userRepository.save(user);

        return customer;
    }

    @Override
    public List<Card> getCards(int userId) {
        List<Card> result = new ArrayList<>();
        Customer customer = getCustomer(userId, true);

        if (customer == null) {
            return result;
        }

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

    @Override
    public Card createCard(int userId, PaymentModel cardInfo) throws StripeException {
        Customer customer = this.getCustomer(userId, true);
        if (customer == null) {
            this.createCustomer(userId);
            customer = getCustomer(userId, true);
        }

        Card newCard = null;
        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("number", cardInfo.getNumber());
        cardMap.put("exp_month", cardInfo.getDue().getMonth() + 1);
        cardMap.put("exp_year", cardInfo.getDue().getYear() + 1900);
        cardMap.put("cvc", cardInfo.getCvv());
        cardMap.put("name", cardInfo.getFullname());
        cardMap.put("address_country", cardInfo.getAddressRegister());
        cardMap.put("address_zip", cardInfo.getPostalCode());
        // brand stripe can auto detect

        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("card", cardMap);
        Token token = Token.create(cardParams);

        Map<String, Object> attachCardMap = new HashMap<>();
        attachCardMap.put("source", token.getId());

        newCard = (Card) customer.getSources().create(attachCardMap);

        return newCard;
    }

    @Override
    public boolean deleteCard(int userId, String stripeCardId) throws StripeException {
        Customer customer = this.getCustomer(userId, true);
        Card card = (Card) customer.getSources().retrieve(stripeCardId);
        card.delete();
        return true;
    }

    @Override
    public boolean checkout(int userId, BigDecimal total, String stripeCardId) throws StripeException{
        BigDecimal totalUsd = total.divide(BigDecimal.valueOf(2000));
        Customer customer = getCustomer(userId, true);
        if (customer == null) {
            return false;
        }

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", totalUsd.multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.HALF_UP));
        chargeParams.put("currency", "usd");
        chargeParams.put("source", stripeCardId);
        chargeParams.put("customer", customer.getId());

        Charge charge = Charge.create(chargeParams);

        return true;
    }
}
