package com.apt.p2p.service;

import com.apt.p2p.entity.StatusHistory;

import java.util.HashMap;

public interface StatusHistoryService {
    HashMap<Integer, StatusHistory> findStatusOrderMapStatusHistoryByOrderId(int orderId);
}
