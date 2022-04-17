package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.CardMapper;
import com.apt.p2p.model.view.CardModel;
import com.apt.p2p.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private StripeService stripeService;
    @Autowired
    private CardMapper cardMapper;

    @Override
    public CardModel create(int userId, CardModel cardModel) throws StripeException {
        Card card = stripeService.createCard(userId, cardModel);

        return cardMapper.stripeCardToCardModel(card);
    }

    @Override
    public List<CardModel> findAllByUserId(int userId) {
        List<Card> cards = stripeService.getCards(userId);

        List<CardModel> result = cards.stream().map(ca -> cardMapper.stripeCardToCardModel(ca)).collect(Collectors.toList());

        return result;
    }

    @Override
    public boolean delete(String stripeCardId) {
        int userId = 3;
        boolean result = false;
        try {
            stripeService.deleteCard(userId, stripeCardId);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
