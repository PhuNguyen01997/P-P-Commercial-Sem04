package com.apt.p2p.repository.Custom;

import com.apt.p2p.entity.Address;

public class AddressRepositoryCustomImpl implements AddressRepositoryCustom {
    @Override
    public Address createNewAddress(String number, String ward, String district, String province) {
        return new Address(number, ward, district, province);
    }


}
