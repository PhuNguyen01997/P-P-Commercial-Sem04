package com.apt.p2p.service;

import com.apt.p2p.entity.ShopTransaction;
import com.apt.p2p.model.form.FilterShopTransaction;
import com.apt.p2p.model.view.ShopTransactionModel;

import java.util.List;

public interface ShopTransactionService{
    List<ShopTransactionModel> findAll();

    List<ShopTransactionModel> findAllByShopIdWithFilter(int shopId, FilterShopTransaction filter);
}
