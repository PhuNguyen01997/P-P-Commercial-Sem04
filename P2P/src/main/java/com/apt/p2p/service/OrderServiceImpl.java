package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.Cart;
import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.OrderDetail;
import com.apt.p2p.entity.Product;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.CartRepository;
import com.apt.p2p.repository.OrderDetailRepository;
import com.apt.p2p.repository.OrderRepository;
import com.apt.p2p.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderModel create(PurchaseModel purchaseModel) {
        List<Cart> carts = cartRepository.findAllById(purchaseModel.getCartIds());
        List<OrderDetail> orderDetails = carts.stream().map(c -> {
            Product product = productRepository.findById(c.getProduct().getId()).get();
            OrderDetail orderDetail = new OrderDetail(c.getProduct().getPrice(), c.getQuantity());
            orderDetail.setProduct(product);
            orderDetailRepository.save(orderDetail);

            return orderDetail;
        }).collect(Collectors.toList());

//        Order order = new Order();
//        Order orderEntity =  orderMapper.orderModelToEntity(model);
//        Order result = orderRepository.save(orderEntity);
//        return orderMapper.orderEntityToModel(result);
        return null;
    }

    @Override
    public OrderModel updateStatus(int statusId) {
        return null;
    }
}
