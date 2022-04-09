package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.RateMapper;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.model.form.PagiSortModel;
import com.apt.p2p.model.view.RateModel;
import com.apt.p2p.repository.RateRepository;
import com.apt.p2p.repository.RateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRpository;
    @Autowired
    private RateMapper rateMapper;

    @Override
    public List<RateModel> findAllByProductId(int productId) {
        return rateRpository.findByProductId(productId).stream().map(re -> rateMapper.rateEntityToModel(re)).collect(Collectors.toList());
    }

    @Override
    public Page<Rate> findAllByProductId(int productId, PagiSortModel pagiSortModel) {
        Specification<Rate> condition = Specification.where(RateSpecification.hasProductId(productId));

        Pageable pageable = null;
        if(pagiSortModel.getSortBy() != null){
            pageable = PageRequest.of(pagiSortModel.getPage(), pagiSortModel.getSize(), pagiSortModel.getSortDirection() ? Sort.Direction.ASC : Sort.Direction.DESC, pagiSortModel.getSortBy());
        }
        else{
            pageable = PageRequest.of(pagiSortModel.getPage(), pagiSortModel.getSize());
        }

        Page<Rate> rates = rateRpository.findAll(condition, pageable);
        return rates;
    }

    public Integer countByShopId(int shopId){
        return rateRpository.countByShopId(shopId);
    }
}
