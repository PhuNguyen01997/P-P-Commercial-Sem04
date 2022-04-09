package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import com.apt.p2p.model.view.AddressModel;

import java.util.List;

public interface AddressService {
    List<AddressModel> findAll();

    List<AddressModel> findAllByUserId(int userId);

    AddressModel save(int userId, AddressModel address);

    AddressModel findById(int id);

    AddressModel update(AddressModel addressModel);

    boolean delete(int id);
}
