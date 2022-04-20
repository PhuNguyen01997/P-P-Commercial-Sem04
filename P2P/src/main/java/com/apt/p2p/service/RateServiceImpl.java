package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.RateMapper;
import com.apt.p2p.entity.*;
import com.apt.p2p.model.form.FilterRatePortal;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RateMapper rateMapper;

    @Override
    public List<RateModel> findAllByProductId(int productId) {
        return rateRepository.findByProductId(productId).stream().map(re -> rateMapper.rateEntityToModel(re)).collect(Collectors.toList());
    }

    @Override
    public Page<Rate> findAllByProductId(int productId, PagiSortModel pagiSortModel) {
        Specification<Rate> condition = Specification.where(RateSpecification.hasProductId(productId));

        Pageable pageable = null;
        if (pagiSortModel.getSortBy() != null) {
            pageable = PageRequest.of(pagiSortModel.getPage(), pagiSortModel.getSize(), pagiSortModel.getSortDirection() ? Sort.Direction.ASC : Sort.Direction.DESC, pagiSortModel.getSortBy());
        } else {
            pageable = PageRequest.of(pagiSortModel.getPage(), pagiSortModel.getSize());
        }

        Page<Rate> rates = rateRepository.findAll(condition, pageable);
        return rates;
    }

    public Integer countByShopId(int shopId) {
        return rateRepository.countByShopId(shopId);
    }

    @Override
    public RateModel create(int userId, int productId, int orderId, RateModel rateModel) {
        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();
        Order order = orderRepository.findById(orderId).get();

        Rate newRate = new Rate();

        newRate.setDescription(rateModel.getDescription());
        newRate.setStar(rateModel.getStar());
        newRate.setUser(user);
        newRate.setProduct(product);
        newRate.setOrder(order);

        rateRepository.save(newRate);

        return rateMapper.rateEntityToModel(newRate);
    }

    @Override
    public List<RateModel> findAllByOrderId(int orderId) {
        List<Rate> finalRates = rateRepository.findAllByOrderId(orderId);

        return finalRates.stream().map(re -> rateMapper.rateEntityToModel(re)).collect(Collectors.toList());
    }

    @Override
    public List<RateModel> findAllByFilterPortal(FilterRatePortal filter) {
        Specification<Rate> condition = Specification
                .where(RateSpecification.hasShopId(filter.getShopId()))
                .and(RateSpecification.hasDateIn(filter.getMinDate(), filter.getMaxDate()))
                .and((root, query, cb) -> {
                    query.orderBy(cb.desc(root.get("id")));
                    return null;
                });

        if(filter.getUserName() != null){
            condition = condition.and(RateSpecification.hasUsername(filter.getUserName()));
        }

        if(filter.getProductName() != null){
            condition = condition.and(RateSpecification.hasProductName(filter.getProductName()));
        }

        if(filter.getStar() != null){
            condition = condition.and(RateSpecification.hasStar(filter.getStar()));
        }

        List<Rate> ratesEntity =  rateRepository.findAll(condition);
        return ratesEntity.stream().map(e -> rateMapper.rateEntityToModel(e)).collect(Collectors.toList());
    }
}
