package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    Address createNewAddress(Address address);

    void deleteAddress(Integer id);

    Address editAddress(Address address);

    List<Address> getAddress();

    Address getAddressDetail(Integer id);
}
