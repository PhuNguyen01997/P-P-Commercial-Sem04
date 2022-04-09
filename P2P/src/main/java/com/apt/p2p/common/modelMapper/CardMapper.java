package com.apt.p2p.common.modelMapper;

import com.apt.p2p.common.StringProcessForView;
import com.apt.p2p.model.view.CardModel;
import com.stripe.model.Card;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardMapper {
    @Autowired
    private MapperService mapperService;

//    @Autowired
//    @Qualifier("hideCardNumber")
//    private Converter hideCardNumberConverter;

    @Autowired
    @Qualifier("removeSpaceNumber")
    private Converter removeSpaceNumber;

    @Autowired
    @Qualifier("setImageCard")
    private Converter setImageCard;

//    public Card cardModelToEntity(CardModel model) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(CardModel.class, Card.class);
//        mapper.addMappings(new PropertyMap<CardModel, Card>() {
//            @Override
//            protected void configure() {
//                skip(destination.getShop());
//                skip(destination.getUser());
//                skip(destination.getOrders());
//                using(removeSpaceNumber).map(source.getPostalCode()).setPostalCode("error");
//                using(removeSpaceNumber).map(source.getCvv()).setCvv("error");
//                using(removeSpaceNumber).map(source.getNumber()).setNumber("error");
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(model, Card.class);
//    }

//    public CardModel cardEntityToModel(Card entity) {
//        ModelMapper mapper = mapperService.getModelMapper();
//        mapper.typeMap(Card.class, CardModel.class);
//        mapper.addMappings(new PropertyMap<Card, CardModel>() {
//            @Override
//            protected void configure() {
//                skip(destination.getShop());
//                skip(destination.getUser());
//                skip(destination.getOrders());
//                using(setImageCard).map(source.getType(), destination.getImgUrl());
//                using(hideCardNumberConverter).map(source.getNumber()).setNumber("Error mapping");
//            }
//        });
//
//        mapper.validate();
//        return mapper.map(entity, CardModel.class);
//    }

    public CardModel stripeCardToCardModel(Card card) {
        CardModel result = new CardModel();
        if(card == null){
            return null;
        }
        result.setStripeCardId(card.getId());
        result.setFullname(card.getName());
        result.setLast4(card.getLast4());
        result.setType(card.getBrand());
        result.setDue(new Date(card.getExpYear().intValue(), card.getExpMonth().intValue(), 1));
        result.setAddressRegister(card.getAddressCountry());
        result.setPostalCode(card.getAddressZip());
        result.setImgUrl(StringProcessForView.getImgUrlByType(card.getBrand()));
        return result;
    }
}
