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
    public List<AddressModel> findAllByUserId(int userId) {
        return addressRepository.findAllByUserId(userId).stream()
                .map(ae -> addressMapper.addressEntityToModel(ae))
                .collect(Collectors.toList());
    }

    @Override
    public AddressModel save(int userId, AddressModel address) {
        User user = userRepository.findById(userId).get();

        Address addressEntity = addressMapper.addressModelToEntity(address);
        addressEntity.setUser(user);

        return addressMapper.addressEntityToModel(addressRepository.save(addressEntity));
    }

    @Override
    public AddressModel update(AddressModel addressModel) {
        try {
            Address newAddress = addressMapper.addressModelToEntity(addressModel);
            Address oldAddress = addressRepository.findById(newAddress.getId()).get();
            newAddress.setUser(oldAddress.getUser());
            newAddress.setShop(oldAddress.getShop());
            addressRepository.save(newAddress);
            return addressModel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AddressModel findById(int id) {
        Address entity = addressRepository.findById(id).orElse(null);
        return addressMapper.addressEntityToModel(entity);
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
