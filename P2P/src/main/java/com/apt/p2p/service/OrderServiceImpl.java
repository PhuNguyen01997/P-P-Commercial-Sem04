package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.ShopModel;
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
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ShopFundRepository shopFundRepository;
    @Autowired
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderModel> create(PurchaseModel purchaseModel) {
        int userId = 3;
        List<OrderModel> result = new ArrayList<>();
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

            User user = userRepository.findById(userId).get();

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

            StatusOrder status = statusOrderRepository.findById(1).get();

            BigDecimal sumTotal = BigDecimal.valueOf(0);
            for (Integer shopId : shopMap.keySet()) {
                BigDecimal total = shopMap.get(shopId).stream().map(OrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                sumTotal = sumTotal.add(total);

                Shop shop = shopRepository.findById(shopId).get();

                BigDecimal totalGetPermission = total.subtract(total.multiply(BigDecimal.valueOf(0.05)));
                ShopFund shopFund;
                Optional<ShopFund> shopFundPresent = shopFundRepository.findByShopId(shopId);
                if (shopFundPresent.isPresent()) {
                    shopFund = shopFundPresent.get();
                    shopFund.setFund(shopFund.getFund().add(totalGetPermission));
                } else {
                    shopFund = new ShopFund(shop, totalGetPermission);
                }
                shopFundRepository.save(shopFund);

                Order order = new Order(purchaseModel.getMethodPayment(), total, user, orderDetails, shop, address, stripeCardId, shopFund, status);
                orderRepository.save(order);

                // attach orderDetails to order
                shopMap.get(shopId).forEach(ode -> ode.setOrder(order));

                result.add(orderMapper.orderEntityToModel(order));
            }

            // Stripe charge
            if (purchaseModel.getMethodPayment()) {
                stripeService.checkout(user.getId(), sumTotal, stripeCardId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result.size() <= 0 ? null : result;
    }

    @Override
    public OrderModel updateStatus(int statusId) {
        return null;
    }

    @Override
    public List<OrderModel> findAllByUserId(int userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        List<OrderModel> orderModels = orders.stream().map(od -> {
            OrderModel model = orderMapper.orderEntityToModel(od);

            List<OrderDetailModel> orderDetails = orderDetailService.findAllByOrderId(model.getId());
            for (OrderDetailModel ode : orderDetails) {
                ProductModel productModel = productService.findByOrderDetailId(ode.getId());
                ode.setProduct(productModel);
            }

            ShopModel shopModel = shopService.findByOrderId(model.getId());
            model.setShop(shopModel);

            return model;
        }).collect(Collectors.toList());

        return orderModels;
    }
}
