package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.AddressMapper;
import com.apt.p2p.common.modelMapper.CardMapper;
import com.apt.p2p.entity.Address;
import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.CardModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.repository.AddressRepository;
import com.apt.p2p.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
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
    private AddressRepository addressRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressMapper addressMapper;

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
