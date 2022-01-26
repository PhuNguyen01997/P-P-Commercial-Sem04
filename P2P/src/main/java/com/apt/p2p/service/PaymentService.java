package com.apt.p2p.service;

import com.apt.p2p.model.view.CartIndexViewModel;

import java.util.List;

public interface PaymentService {
    List<CartIndexViewModel> processViewPayment(String[] shopCardIdList);
}