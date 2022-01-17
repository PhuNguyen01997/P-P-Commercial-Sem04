package com.apt.p2p.service;

import com.stripe.model.Customer;

public interface StripeService {
    public boolean checkout();
    public Customer createCustomer(int userId);
    public Customer findCustomer(String customerId);
}
