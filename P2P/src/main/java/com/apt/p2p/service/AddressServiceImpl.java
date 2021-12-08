package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import com.apt.p2p.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address createNewAddress(Address address) {
        addressRepository.save(addressRepository.createNewAddress(address.getNumber(), address.getWard(), address.getDistrict(), address.getProvince()));
        return addressRepository.createNewAddress(address.getNumber(), address.getWard(), address.getDistrict(), address.getProvince());
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteAddressById(id);
    }

    @Override
    public Address editAddress(Address address) {
        Address addressChild = addressRepository.findById(address.getId());
        addressChild.setNumber(address.getNumber());
        addressChild.setWard(address.getWard());
        addressChild.setDistrict(address.getDistrict());
        addressChild.setProvince(address.getProvince());
        return addressChild;
    }

    @Override
    public List<Address> getAddress() {
        return null;
    }

    @Override
    public Address getAddressDetail(Integer id) {
        return null;
    }
}
