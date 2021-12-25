package com.apt.p2p.service;

import com.apt.p2p.model.RateModel;

import java.util.List;

public interface RateService {
    public List<RateModel> findByProductId(int productId);
}
