package com.apt.p2p.service;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.StatusHistory;
import com.apt.p2p.entity.StatusOrder;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.OrderRepository;
import com.apt.p2p.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StatusOrderServiceImpl implements StatusOrderService {
    @Autowired
    private StatusOrderRepository statusOrderRepository;

    @Override
    public List<StatusOrder> findAll() {
        return statusOrderRepository.findAll();
    }
}
