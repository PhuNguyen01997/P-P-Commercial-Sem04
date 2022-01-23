package com.apt.p2p.service;

import com.apt.p2p.model.view.CartIndexViewModel;
import com.apt.p2p.model.view.PaymentModel;
import com.stripe.exception.StripeException;

import java.util.List;

public interface PaymentService {
    public PaymentModel create(PaymentModel paymentModel) throws StripeException;

//    public List<PaymentModel> findAll();

    public List<PaymentModel> findAllByUserId(int id);

    public boolean delete(String stripeCardId);

    public List<CartIndexViewModel> processViewPayment(String[] shopCardIdList);
}