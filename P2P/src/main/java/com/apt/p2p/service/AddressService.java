package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import com.apt.p2p.model.view.AddressModel;

import java.util.List;

public interface AddressService {
    List<AddressModel> findAll();

    List<AddressModel> findByUserId(int userId);

    AddressModel save(AddressModel address);

    AddressModel findById(int id);

    boolean delete(int id);
}
