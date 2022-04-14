package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.ShopTransactionMapper;
import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.model.form.FilterShopTransaction;
import com.apt.p2p.model.view.OrderDetailModel;
import com.apt.p2p.model.view.OrderModel;
import com.apt.p2p.model.view.ShopTransactionModel;
import com.apt.p2p.repository.OrderSpecification;
import com.apt.p2p.repository.ShopTransactionRepository;
import com.apt.p2p.repository.ShopTransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopTransactionServiceImpl implements ShopTransactionService {
    @Autowired
    private ShopTransactionRepository shopTransactionRepository;
    @Autowired
    private ShopTransactionMapper shopTransactionMapper;

    @Override
    public List<ShopTransactionModel> findAll() {
        return shopTransactionRepository.findAll().stream().map(st -> shopTransactionMapper.shopTransactionEntityToModel(st)).collect(Collectors.toList());
    }

    @Override
    public List<ShopTransactionModel> findAllByShopIdWithFilter(int shopId, FilterShopTransaction filter) {
        Specification<ShopTransaction> condition = Specification
                .where(ShopTransactionSpecification.hasShopId(shopId))
                .and(ShopTransactionSpecification.hasDateIn(filter.getMinDate(), filter.getMaxDate()))
                .and((root, query, cb) -> {
                    query.orderBy(cb.desc(root.get("id")));
                    return null;
                });

        switch (filter.getType()) {
            case 1: {
                condition = condition.and(ShopTransactionSpecification.hasOrder());
                break;
            }
            case 2: {
                condition = condition.and(ShopTransactionSpecification.hasNoOrder());
                break;
            }
        }

        List<ShopTransaction> transactions = shopTransactionRepository.findAll(condition);

        List<ShopTransactionModel> transactionModels = transactions.stream().map(tr -> {
            ShopTransactionModel model = shopTransactionMapper.shopTransactionEntityToModel(tr);
            return model;
        }).collect(Collectors.toList());

        return transactionModels;
    }
}
