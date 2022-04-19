package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.OrderMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.FilterOrder;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.form.PurchaseModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private StatusOrderRepository statusOrderRepository;
    @Autowired
    private StatusHistoryRepository statusHistoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ShopTransactionRepository shopTransactionRepository;
    @Autowired
    private StripeService stripeService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public List<OrderModel> create(int userId, PurchaseModel purchaseModel) {
        List<OrderModel> result = new ArrayList<>();
        Boolean isPaymentOnline = purchaseModel.getMethodPayment();
        try {
            // handle save to db
            List<Cart> carts = cartRepository.findAllById(Arrays.asList(purchaseModel.getCartIds()));
            List<OrderDetail> orderDetails = carts.stream().map(c -> {
                Product product = productRepository.findById(c.getProduct().getId()).get();
                OrderDetail orderDetail = new OrderDetail(c.getProduct().getPrice(), c.getQuantity());
                orderDetail.setProduct(product);

                return orderDetail;
            }).collect(Collectors.toList());

            User user = userRepository.findById(userId).get();

            Address address = addressRepository.findById(purchaseModel.getAddressId()).get();

            String stripeCardId = isPaymentOnline ? purchaseModel.getStripeCardId() : null;

            StatusOrder status = statusOrderRepository.findById(isPaymentOnline ? 2 : 1).get();

            BigDecimal sumTotal = BigDecimal.valueOf(0);
            Integer tempIndex = 0;
            List<Order> orderCreates = new ArrayList<>();
            List<StatusHistory> statusCreates = new ArrayList<>();
            List<ShopTransaction> transactionCreates = new ArrayList<>();
            HashMap<Integer, BigDecimal> mapShopFund = new HashMap<>();
            for (Integer shopId : purchaseModel.getShopIds()) {
                List<OrderDetail> filterOrderDetails = orderDetails
                        .stream().filter(ode -> ode.getProduct().getShop().getId() == shopId)
                        .collect(Collectors.toList());

                BigDecimal shipCost = purchaseModel.getShipping()[tempIndex];
                BigDecimal total = filterOrderDetails.stream().map(OrderDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalWithShipCost = total.add(shipCost);

                sumTotal = sumTotal.add(totalWithShipCost);

                Shop shop = shopRepository.findById(shopId).get();

                BigDecimal totalGetPermission = totalWithShipCost.subtract(totalWithShipCost.multiply(BigDecimal.valueOf(0.05)));
                if(isPaymentOnline){
                    mapShopFund.put(shopId, totalGetPermission);
                }

                Order order = new Order(isPaymentOnline, totalWithShipCost, shipCost, user, orderDetails, status, shop, address, stripeCardId);

                // attach many to many relation ship order - order_status_history - status_history
                for (int i = 1; i <= status.getId(); i++) {
                    StatusOrder statusSaveToHistory = statusOrderRepository.findById(i).get();
                    StatusHistory statusHistory = new StatusHistory(statusSaveToHistory, order);
                    statusCreates.add(statusHistory);
                }

                // attach orderDetails to order
                filterOrderDetails.forEach(ode -> ode.setOrder(order));

                // save transaction history
                if(isPaymentOnline){
                    ShopTransaction transaction = new ShopTransaction(shop, order);
                    transactionCreates.add(transaction);
                }

                result.add(orderMapper.orderEntityToModel(order));

                tempIndex++;
                orderCreates.add(order);
            }

            // Stripe charge
            if (isPaymentOnline) {
                stripeService.checkout(user.getUserId(), sumTotal, stripeCardId);
            }

            orderDetailRepository.saveAll(orderDetails);
            orderRepository.saveAll(orderCreates);
            statusHistoryRepository.saveAll(statusCreates);
            shopTransactionRepository.saveAll(transactionCreates);
            mapShopFund.keySet().forEach(shopId -> {
                Shop shop = shopRepository.findById(shopId).get();
                shop.setFund(shop.getFund().add(mapShopFund.get(shopId)));
                shopRepository.save(shop);
            });
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

            StatusHistory statusHistory = new StatusHistory(statusOrder, order);
            statusHistoryRepository.save(statusHistory);

            order.getStatusHistories().add(statusHistory);

            orderRepository.save(order);

            result = true;
        }
        return result;
    }

    @Override
    public List<OrderModel> findAllByUserId(int userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        List<OrderModel> orderModels = orders.stream()
                .map(o -> {
                    OrderModel model = orderMapper.orderEntityToModel(o);
                    model.setOrderDetails(orderDetailService.findAllByOrderId(model.getId()));
                    return model;
                })
                .collect(Collectors.toList());

        return orderModels;
    }

    @Override
    public Page<Order> findAllByUserId(int userId, PagiSortModel pagiSortModel) {
        Specification<Order> condition = Specification.where(OrderSpecification.hasUserId(userId));

        Pageable pageable = PageRequest.of(pagiSortModel.getPage(), pagiSortModel.getSize(), Sort.Direction.DESC, "id");

        Page<Order> orderEntities = orderRepository.findAll(condition, pageable);

        return orderEntities;
    }

    @Override
    public OrderModel findById(int orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order order = optionalOrder.orElse(null);

        if (order == null) {
            return null;
        }

        OrderModel orderModel = orderMapper.orderEntityToModel(order);
        orderModel.setOrderDetails(orderDetailService.findAllByOrderId(orderModel.getId()));

        for (StatusHistory sh : orderModel.getStatusHistories()) {
            sh.setStatus(statusOrderRepository.findByStatusHistory(sh.getId()));
        }

        return orderModel;
    }

    @Override
    public List<OrderModel> findALlByShopId(int shopId) {
        List<Order> orders = orderRepository.findAllByShopId(shopId);
        List<OrderModel> orderModels = orders.stream()
                .map(o -> {
                    OrderModel model = orderMapper.orderEntityToModel(o);
                    model.setOrderDetails(orderDetailService.findAllByOrderId(model.getId()));
                    return model;
                })
                .collect(Collectors.toList());

        return orderModels;
    }

    @Override
    public List<OrderModel> findAllByShopIdWithFilter(int shopId, FilterOrder filterOrder) {
        Specification<Order> condition = Specification
                .where(OrderSpecification.hasShopId(shopId))
                .and(OrderSpecification.hasDateIn(filterOrder.getMinDate(), filterOrder.getMaxDate()))
                .and((root, query, cb) -> {
                    query.orderBy(cb.desc(root.get("id")));
                    return null;
                });

        if (filterOrder.getStatusId() != 0) {
            condition = condition.and(OrderSpecification.hasStatusId(filterOrder.getStatusId()));
        }

        if (!filterOrder.getName().isEmpty()) {
            condition = condition.and(OrderSpecification.likeNameByType(filterOrder.getFilterBy(), filterOrder.getName()));
        }

        List<Order> orders = orderRepository.findAll(condition);
        // trick dump for join orderDetails and status history
        for (Order o : orders) {
            int size1 = o.getStatusHistories().size();
            int size2 = o.getOrderDetails().size();
        }

        List<OrderModel> orderModels = orders.stream().map(o -> {
            OrderModel model = orderMapper.orderEntityToModel(o);
            model.setOrderDetails(orderDetailService.findAllByOrderId(model.getId()));
            return model;
        }).collect(Collectors.toList());

        return orderModels;
    }
}
