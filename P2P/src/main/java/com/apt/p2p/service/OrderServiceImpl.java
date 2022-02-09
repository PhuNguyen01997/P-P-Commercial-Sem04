package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ProductModel;
import com.apt.p2p.model.view.ShopModel;
import com.apt.p2p.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private OrderStatusOrderRepository orderStatusOrderRepository;
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
    @Transactional
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

            Address address = addressRepository.findById(purchaseModel.getAddressId()).get();

            String stripeCardId = purchaseModel.getMethodPayment() ? purchaseModel.getStripeCardId() : null;

            StatusOrder status = statusOrderRepository.findById(1).get();

            BigDecimal sumTotal = BigDecimal.valueOf(0);
            Integer tempIndex = 0;
            for (Integer shopId : purchaseModel.getShopIds()) {
                List<OrderDetail> filterOrderDetails = orderDetails
                        .stream().filter(ode -> ode.getProduct().getShop().getId() == shopId)
                        .collect(Collectors.toList());

                BigDecimal shipCost = purchaseModel.getShipping()[tempIndex];
                BigDecimal total = filterOrderDetails.stream().map(OrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalWithShipCost = total.add(shipCost);

                sumTotal = sumTotal.add(totalWithShipCost);

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

                Order order = new Order(purchaseModel.getMethodPayment(), total, shipCost, user, orderDetails, status, shop, address, stripeCardId, shopFund);
                orderRepository.save(order);

                // attach many to many relation ship order - order_status_order - status_order
                OrderStatusOrder orderStatusOrder = new OrderStatusOrder();
                orderStatusOrder.setStatus(status);
                orderStatusOrder.setOrder(order);
                orderStatusOrderRepository.save(orderStatusOrder);

                // attach orderDetails to order
                filterOrderDetails.forEach(ode -> ode.setOrder(order));

                result.add(orderMapper.orderEntityToModel(order));

                tempIndex++;
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
    @Transactional
    public boolean updateStatus(int orderId, int statusId) {
        boolean result = false;
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            StatusOrder statusOrder = statusOrderRepository.findById(statusId).get();
            order.setCurrentStatus(statusOrder);

            OrderStatusOrder orderStatusOrder = new OrderStatusOrder(statusOrder, order);
            orderStatusOrderRepository.save(orderStatusOrder);

            order.getOrderStatusOrders().add(orderStatusOrder);

            orderRepository.save(order);

            result = true;
        }
        return result;
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

            model.setOrderDetails(orderDetails);

            ShopModel shopModel = shopService.findByOrderId(model.getId());
            model.setShop(shopModel);

            return model;
        }).collect(Collectors.toList());

        return orderModels;
    }

    @Override
    public OrderModel findById(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElse(null);

        if (order == null) {
            return null;
        }

        OrderModel orderModel = orderMapper.orderEntityToModel(order);
        orderModel.setOrderDetails(orderDetailService.findAllByOrderId(orderId));

        return orderModel;
    }

    @Override
    public List<OrderModel> findALlByShopId(int shopId) {
        List<Order> orders = orderRepository.findAllByShopId(shopId);
        List<OrderModel> orderModels = orders.stream().map(od -> {
            OrderModel model = orderMapper.orderEntityToModel(od);

            List<OrderDetailModel> orderDetails = orderDetailService.findAllByOrderId(model.getId());
            for (OrderDetailModel ode : orderDetails) {
                ProductModel productModel = productService.findByOrderDetailId(ode.getId());
                ode.setProduct(productModel);
            }

            model.setOrderDetails(orderDetails);

            return model;
        }).collect(Collectors.toList());

        return orderModels;
    }

    @Override
    public List<OrderModel> findAllByShopIdWithFilter(int shopId, FilterOrder filterOrder) {
        Specification<Order> condition = Specification
                .where(OrderSpecification.hasShopId(shopId))
                .and(OrderSpecification.hasDateIn(filterOrder.getMinDate(), filterOrder.getMaxDate()));

        if (filterOrder.getStatusId() != 0) {
            condition = condition.and(OrderSpecification.hasStatusId(filterOrder.getStatusId()));
        }

        if (!filterOrder.getName().isEmpty()) {
            condition = condition.and(OrderSpecification.likeNameByType(filterOrder.getFilterBy(), filterOrder.getName()));
        }

        List<Order> orders = orderRepository.findAll(condition);

        List<OrderModel> orderModels = orders.stream().map(o -> {
            OrderModel model = orderMapper.orderEntityToModel(o);
            for (OrderDetailModel ode : model.getOrderDetails()) {
                ode.setProduct(productService.findByOrderDetailId(ode.getId()));
            }
            return model;
        }).collect(Collectors.toList());

        return orderModels;
    }
}
