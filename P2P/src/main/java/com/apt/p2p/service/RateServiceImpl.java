package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.RateMapper;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository repository;
    @Autowired
    private RateMapper rateMapper;

    @Override
    public List<RateModel> findByProductId(int productId) {
        return repository.findByProductId(productId).stream().map(re -> rateMapper.rateEntityToModel(re)).collect(Collectors.toList());
    }

    public Integer countByShopId(int shopId){
        return repository.countByShopId(shopId);
    }
}
