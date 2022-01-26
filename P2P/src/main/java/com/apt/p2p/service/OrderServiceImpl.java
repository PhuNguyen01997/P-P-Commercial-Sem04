package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
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
    private ShopFundRepository shopFundRepository;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderModel create(PurchaseModel purchaseModel) {
        int userId = 3;
        OrderModel result = null;
        try {
            // handle save to db
            List<Cart> carts = cartRepository.findAllById(Arrays.asList(purchaseModel.getCartIds()));
            List<OrderDetail> orderDetails = carts.stream().map(c -> {
                Product product = productRepository.findById(c.getProduct().getId()).get();
                OrderDetail orderDetail = new OrderDetail(c.getProduct().getPrice(), c.getQuantity());
                orderDetail.setProduct(product);
                orderDetailRepository.save(orderDetail);

                return orderDetail;
            }).collect(Collectors.toList());

            BigDecimal total = orderDetails.stream().map(OrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);

            User user = userRepository.findById(userId).get();

//            List<Integer> productIdList = orderDetails.stream().map(od -> od.getProduct().getId()).collect(Collectors.toList());
//            List<Shop> shops = shopRepository.findAllByProductId(productIdList);
            HashMap<Integer, List<OrderDetail>> shopMap = new HashMap<>();
            for (OrderDetail orderDetail : orderDetails) {
                Shop shop = shopRepository.findByOrderDetailId(orderDetail.getId());
                if (shopMap.get(shop.getId()) == null) {
                    List<OrderDetail> odeList = new ArrayList<>();
                    odeList.add(orderDetail);
                    shopMap.put(shop.getId(), odeList);
                } else {
                    shopMap.get(shop.getId()).add(orderDetail);
                }
            }

            Address address = addressRepository.findById(purchaseModel.getAddressId()).get();

            String stripeCardId = purchaseModel.getMethodPayment() ? purchaseModel.getStripeCardId() : null;

//            LocalDate localDate = LocalDate.now();
//            ShopFund shopFund;
//            Optional<ShopFund> debtPresent = orderDebtRepository.findByUserIdAndMonth(userId, localDate.getMonthValue(), localDate.getYear());
//            BigDecimal plusValue = total.multiply(BigDecimal.valueOf(0.05));
//            if (debtPresent.isPresent()) {
//                shopFund = debtPresent.get();
//                shopFund.setTotal(shopFund.getTotal().add(plusValue));
//            } else {
//                shopFund = new ShopFund(plusValue);
//                shopFund.setUser(user);
//            }
//            orderDebtRepository.save(shopFund);
            for (Integer shopId : shopMap.keySet()) {
//                ShopFund shopFund;
//                Optional<ShopFund> shopFundPresent = shopFundRepository.findByShopId(shop.getId());
//                if (shopFundPresent.isPresent()) {
//                    shopFund = shopFundPresent.get();
//                    shopFund.setFund();
//                }
            }

            StatusOrder status = statusOrderRepository.findById(1).get();

            Order order = new Order(purchaseModel.getMethodPayment(), total, user, orderDetails, shops, address, stripeCardId, shopFund, status);
            orderRepository.save(order);

            // attach orderDetails to order
            orderDetails.forEach(od -> od.setOrder(order));

            result = orderMapper.orderEntityToModel(order);

            // Stripe charge
            if (purchaseModel.getMethodPayment()) {
                stripeService.checkout(user.getId(), total, stripeCardId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    public OrderModel updateStatus(int statusId) {
        return null;
    }

    @Override
    public List<OrderModel> findAllByUserId(int userId, boolean joinOrderDetailAndProduct, boolean joinShop) {
//        List<Order> orders = orderRepository.findAllByUserId(userId);
//        List<OrderModel> orderModels = orders.stream().map(od -> orderMapper.orderEntityToModel(od)).collect(Collectors.toList());
//
//        for (OrderModel order : orderModels) {
//            if (joinOrderDetailAndProduct) {
//                order.setOrderDetails(orderDetailService.findAllByOrderId(order.getId()));
//
//                for (OrderDetailModel ode : order.getOrderDetails()) {
//                    ode.setProduct(productService.findByOrderDetailId(ode.getId()));
//                }
//            }
//            if (joinShop) {
//                List<ShopModel> shopModels = shopService.findByOrderId()
//                order.setShops();
//            }
//        }
        return null;
    }
}
