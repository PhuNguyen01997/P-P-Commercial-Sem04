package com.apt.p2p.service;

import com.stripe.model.Card;
import com.stripe.model.Customer;

import java.util.List;

public interface StripeService {
//    public boolean checkout();
//    public Customer createCustomer(int userId);
//    public Customer findCustomer(String customerId);

    public Customer getCustomer(int userId, boolean withSource);

    public List<Card> getCards(int userId);
}
