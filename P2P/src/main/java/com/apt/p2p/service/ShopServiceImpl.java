package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ShopMapper;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository repository;
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public ShopModel findByProductId(int productId) {
        ShopModel result = null;
        result = shopMapper.shopEntityToModel(repository.findByProductId(productId));
        return result;
    }

//    @Override
//    public
}
