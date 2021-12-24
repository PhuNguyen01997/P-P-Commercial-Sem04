package com.apt.p2p.service;

import com.apt.p2p.entity.Address;
import com.apt.p2p.model.AddressModel;
import org.mapstruct.Mapper;

//@Mapper
public interface AddressMapper {
    AddressModel addressToAddressModel(Address address);

    Address addressModelToAddress(AddressModel addressModel);
}
