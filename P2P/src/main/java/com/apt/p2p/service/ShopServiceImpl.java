package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.model.view.UserModel;
import com.apt.p2p.repository.ShopRepository;
import com.apt.p2p.repository.ShopTransactionRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopTransactionRepository shopTransactionRepository;
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public ShopModel findByProductId(int productId) {
        ShopModel result = null;
        result = shopMapper.shopEntityToModel(shopRepository.findByProductId(productId));
        return result;
    }

    @Override
    public ShopModel findByOrderId(int orderId) {
        ShopModel result = null;
        Shop shop = shopRepository.findByOrderId(orderId);
        if (shop != null) {
            result = shopMapper.shopEntityToModel(shop);
        }
        return result;
    }

    @Override
    public ShopModel findById(int id) {
        Shop shop = shopRepository.findById(id).orElse(null);

        return shopMapper.shopEntityToModel(shop);
    }

    @Override
    @Transactional
    public boolean shopWithDraw(int shopId, BigDecimal amount) {
        boolean result = false;

        try {
            Shop shop = shopRepository.findById(shopId).get();

            ShopTransaction shopTransaction = new ShopTransaction(shop, amount);
            shopTransactionRepository.save(shopTransaction);

            shop.setFund(shop.getFund().subtract(amount));
            shopRepository.save(shop);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ShopModel findByUserId(int userId) {
        Shop shop = shopRepository.findByUserId(userId);

        return shopMapper.shopEntityToModel(shop);
    }

    @Override
    public ShopModel createOrUpdate(ShopModel shopModel) {
        Shop shop = null;
        if (shopModel.getId() == 0) {
            shop = new Shop(shopModel);
            User user = userRepository.findById(shop.getUser().getUserId()).get();
            shop.setUser(user);
        } else {
            shop = shopRepository.findById(shopModel.getId()).get();

            shop.setLogo(shopModel.getLogo());
            shop.setName(shopModel.getName());
            shop.setPhone(shopModel.getPhone());
            shop.setFund(shopModel.getFund());
            shop.setPermission(shopModel.getPermission());
            shop.setDescription(shopModel.getDescription());
            shop.setCreatedAt(shopModel.getCreatedAt());
            shop.setUpdatedAt(shopModel.getUpdatedAt());

            shopRepository.save(shop);
        }
        return shopMapper.shopEntityToModel(shop);
    }
}
