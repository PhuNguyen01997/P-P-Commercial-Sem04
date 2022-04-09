package com.apt.p2p.service;

import com.apt.p2p.model.view.CardModel;
import com.stripe.exception.StripeException;

import java.util.List;

public interface CardService {
    public CardModel create(CardModel cardModel) throws StripeException;

    public List<CardModel> findAllByUserId(int id);

    public boolean delete(String stripeCardId);
}
