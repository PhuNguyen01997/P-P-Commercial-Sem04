package com.apt.p2p.service;

import com.apt.p2p.model.view.PaymentModel;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;

import java.util.List;

public interface StripeService {
//    public boolean checkout();
//    public Customer createCustomer(int userId);
//    public Customer findCustomer(String customerId);

    public Customer getCustomer(int userId, boolean withSource);

    public Customer createCustomer(int userId) throws StripeException;

    public List<Card> getCards(int userId);

    public Card createCard(int userId, PaymentModel cardInfo) throws StripeException;

    public boolean deleteCard(int userId, String stripeCardId) throws StripeException;
}
