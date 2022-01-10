package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderDebtRepository orderDebtRepository;
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

        double total = orderDetails.stream().mapToDouble(od -> od.getSubtotal()).sum();

        User user = userRepository.findById(1).get();

        List<Integer> productIdList = orderDetails.stream().map(od -> od.getProduct().getId()).collect(Collectors.toList());
        List<Shop> shops = shopRepository.findAllByProductId(productIdList);

        Address address = addressRepository.findById(purchaseModel.getAddressId()).get();

        Payment payment = paymentRepository.findById(purchaseModel.getPaymentId()).get();

        LocalDate localDate = LocalDate.now();
        OrderDebt orderDebt;
        Optional<OrderDebt> debtPresend = orderDebtRepository.findByUserIdAndMonth(1, localDate.getMonthValue(), localDate.getYear());
        if (debtPresend.isPresent()) {
            orderDebt = debtPresend.get();
            orderDebt.setTotal(orderDebt.getTotal() + (total * 0.05));
        } else {
            orderDebt = new OrderDebt(total * 0.05);
            orderDebt.setUser(user);
        }
        orderDebtRepository.save(orderDebt);

        Order order = new Order(false, total, user, orderDetails, shops, address, payment, orderDebt);
        orderRepository.save(order);

        return orderMapper.orderEntityToModel(order);
    }

    @Override
    public OrderModel updateStatus(int statusId) {
        return null;
    }
}
