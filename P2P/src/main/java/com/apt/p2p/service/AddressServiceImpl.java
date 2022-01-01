package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.AddressMapper;
import com.apt.p2p.entity.Address;
import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressModel> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(ae -> addressMapper.addressEntityToModel(ae))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressModel> findByUserId(int userId) {
        return addressRepository.findByUserId(userId).stream()
                .map(ae -> addressMapper.addressEntityToModel(ae))
                .collect(Collectors.toList());
    }

    @Override
    public AddressModel save(Address address) {
        return null;
    }

    @Override
    public AddressModel findById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
