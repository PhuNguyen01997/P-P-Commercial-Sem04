package com.apt.p2p.service;

import com.apt.p2p.common.modelMapper.AddressMapper;
import com.apt.p2p.entity.Address;
import com.apt.p2p.entity.User;
import com.apt.p2p.model.view.AddressModel;
import com.apt.p2p.repository.AddressRepository;
import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
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
    public AddressModel save(AddressModel address) {
        User user = userRepository.findById(1).get();

        Address addressEntity = addressMapper.addressModelToEntity(address);
        addressEntity.setUser(user);

        return addressMapper.addressEntityToModel(addressRepository.save(addressEntity));
    }

    @Override
    public AddressModel findById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Address address = addressRepository.findById(id).get();
            if (address.getShop() != null) {
                address.setUser(null);
                addressRepository.save(address);
            } else {
                addressRepository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            return true;
        }
    }
}
