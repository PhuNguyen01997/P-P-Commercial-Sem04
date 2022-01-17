package com.apt.p2p.service;

import com.apt.p2p.model.stripe.StripeCustomer;

public interface StripeService {
    public boolean checkout();
    public StripeCustomer createCustomer();
    public StripeCustomer findCustomer(String customerId);
}
