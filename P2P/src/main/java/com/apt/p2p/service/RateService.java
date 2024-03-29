package com.apt.p2p.service;

import com.apt.p2p.entity.Rate;
import com.apt.p2p.model.form.FilterRatePortal;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.RateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RateService {
    List<RateModel> findAllByProductId(int productId);

    Page<Rate> findAllByProductId(int productId, PagiSortModel pagiSortModel);

    Integer countByShopId(int shopId);

    RateModel create(int userId, int productId, int orderId, RateModel rateModel);

    List<RateModel> findAllByOrderId(int orderId);

    List<RateModel> findAllByFilterPortal(FilterRatePortal filterRatePortal);
}
