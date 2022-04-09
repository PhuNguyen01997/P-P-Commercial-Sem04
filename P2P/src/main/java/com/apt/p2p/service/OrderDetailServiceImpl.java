package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderDetailMapper;
import com.apt.p2p.entity.OrderDetail;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailModel> findAllByOrderId(int orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        return orderDetails.stream().map(ode -> orderDetailMapper.orderDetailEntityToModel(ode)).collect(Collectors.toList());
    }
}
