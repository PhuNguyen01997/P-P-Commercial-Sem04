package com.apt.p2p.service;

import com.apt.p2p.model.view.CardModel;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface StripeService {
    public boolean checkout(int userId, BigDecimal total, String stripeCardId) throws StripeException;

    public Customer getCustomer(int userId, boolean withSource);

    public Customer createCustomer(int userId) throws StripeException;

    public List<Card> getCards(int userId);

    public Card createCard(int userId, CardModel cardInfo) throws StripeException;

    public boolean deleteCard(int userId, String stripeCardId) throws StripeException;
}
