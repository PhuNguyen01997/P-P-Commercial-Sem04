package com.apt.p2p.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Value("${stripe.publish.key}")
    private String publishKey;

    @Value("${stripe.secret.key}")
    private String secretKey;

    @GetMapping("/test")
    public String test(Model model) {
//        model.addAttribute("amount", 50 * 100);
//        model.addAttribute("stripePublishKey", publishKey);
//        model.addAttribute("currency", "USD");
        return "user/main/test.html";
    }

    @GetMapping("/add-card")
    public String addCard() {
        Stripe.apiKey = secretKey;
        String customerId = "cus_KvkJ3kC7wpHVlT";
//        try {
//            // get customer
//            Customer customer = Customer.retrieve(customerId);
//
//            Map<String, Object> =
//        } catch (StripeException e) {
//            e.printStackTrace();
//        }

        try {
            Map<String, Object> cardMap = new HashMap<>();
//            cardMap.put("number", "4242424242424242");
            cardMap.put("number", "5555555555554444");
            cardMap.put("exp_month", 1);
            cardMap.put("exp_year", 2023);
//            cardMap.put("cvc", "314");
            cardMap.put("cvc", "538");

            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("card", cardMap);
            Token token = Token.create(cardParams);
            Card card = token.getCard();

            Map<String, Object> retrieveParams = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer = Customer.retrieve(customerId, retrieveParams, null);

            Map<String, Object> attachCardMap = new HashMap<>();
            attachCardMap.put("source", token.getId());

            PaymentSourceCollection source = customer.getSources();

            card = (Card) customer.getSources().create(attachCardMap);

            String s = "";

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return "user/main/test.html";
    }

    @GetMapping("/charge")
    public String charge() {
        Stripe.apiKey = secretKey;
        String customerId = "cus_KvkJ3kC7wpHVlT";

        try {
            Map<String, Object> retrieveParams = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer = Customer.retrieve(customerId, retrieveParams, null);
            PaymentSourceCollection source = customer.getSources();

            Map<String, Object> params = new HashMap<>();
            params.put("amount", 2000);
            params.put("currency", "usd");
            params.put("customer", customer.getId());
            params.put("source", source.getData().get(1).getId());
            params.put(
                    "description",
                    "My First Test Charge (created for API docs)"
            );
            Charge charge = Charge.create(params);

            String s = "";
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return "user/main/test.html";
    }

    @GetMapping("/delete-card")
    public String deleteCard() {
        Stripe.apiKey = secretKey;
        String customerId = "cus_KvkJ3kC7wpHVlT";

        try {
            Map<String, Object> retrieveParams = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer = Customer.retrieve(customerId, retrieveParams, null);
            PaymentSourceCollection source = customer.getSources();

            Card card = (Card) source.retrieve(source.getData().get(1).getId());

            Card deletedCard = (Card) card.delete();

            String s = "";
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return "user/main/test.html";
    }
}
