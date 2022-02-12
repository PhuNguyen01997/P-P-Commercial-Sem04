package com.apt.p2p.service;

import com.apt.p2p.entity.StatusHistory;
import com.apt.p2p.entity.StatusOrder;
import com.apt.p2p.repository.StatusHistoryRepository;
import com.apt.p2p.repository.StatusOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private StatusHistoryRepository statusHistoryRepository;

    @Override
    public HashMap<Integer, StatusHistory> findStatusOrderMapStatusHistoryByOrderId(int orderId) {
        HashMap<Integer, StatusHistory> result = new HashMap<>();
        List<StatusOrder> statusOrderList = statusOrderRepository.findAll();

        statusOrderList.forEach(s -> {
            StatusHistory history = statusHistoryRepository.findByOrderIdAndStatusId(orderId, s.getId());
            result.put(s.getId(), history);
        });
        return result;
    }
}
