package com.apt.p2p.service;

import com.apt.p2p.entity.StatusHistory;
import com.apt.p2p.entity.StatusOrder;

import java.util.HashMap;
import java.util.List;

public interface StatusOrderService {
    List<StatusOrder> findAll();
}