package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.AddressMapper;
import com.apt.p2p.common.modelMapper.PaymentMapper;
import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.Payment;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.PaymentModel;
import com.apt.p2p.model.view.ProductCartModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.repository.AddressRepository;
import com.apt.p2p.repository.PaymentRepository;
import com.apt.p2p.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public PaymentModel create(PaymentModel paymentModel) throws StripeException {
        int userId = 3;
        Card card = stripeService.createCard(userId, paymentModel);

        return paymentMapper.stripeCardToPaymentModel(card);
    }

//    @Override
//    public List<PaymentModel> findAll() {
////        return repository.findAll().stream().map(pe -> paymentMapper.paymentEntityToModel(pe)).collect(Collectors.toList());
//        return null;
//    }

    @Override
    public List<PaymentModel> findAllByUserId(int userId) {
        List<Card> cards = stripeService.getCards(userId);

        List<PaymentModel> result = cards.stream().map(ca -> paymentMapper.stripeCardToPaymentModel(ca)).collect(Collectors.toList());

        return result;
    }

    @Override
    public boolean delete(String stripeCardId) {
        int userId = 3;
        boolean result = false;
        try {
            stripeService.deleteCard(userId, stripeCardId);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
//        try {
//            Payment payment = repository.findById(id).get();
//            if (payment.getShop() != null) {
//                payment.setUser(null);
//                repository.save(payment);
//            } else {
//                repository.deleteById(id);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public List<CartIndexViewModel> processViewPayment(String[] shopCardIdList) {
        List<CartIndexViewModel> result = new ArrayList<>();
        Pattern shopPattern = Pattern.compile("(?<=S)\\d+");
        Pattern cartPattern = Pattern.compile("(?<=C)\\d+");

        for (String str : shopCardIdList) {
            Integer shopId = null;
            Matcher shopMatcher = shopPattern.matcher(str);
            shopMatcher.find();
            shopId = Integer.parseInt(shopMatcher.group());

            Matcher cartMatcher = cartPattern.matcher(str);
            List<Integer> cartIdList = cartMatcher.results().map(matchResult -> Integer.parseInt(matchResult.group())).collect(Collectors.toList());

            result.add(cartService.getCartProductByShopIdAndCartId(shopId, cartIdList));
        }

        result.stream().map(cartIndexViewModel -> {
            ShopModel shop = cartIndexViewModel.getShop();
            Address addressEntity = addressRepository.findByShopId(shop.getId());
            shop.setAddress(addressMapper.addressEntityToModel(addressEntity));
            return cartIndexViewModel;
        });

        return result;
    }
}
