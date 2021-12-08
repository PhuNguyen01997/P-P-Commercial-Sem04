package com.apt.p2p.repository.Custom;

import com.apt.p2p.entity.Address;

public interface AddressRepositoryCustom {
    Address createNewAddress(String number, String ward, String district, String province);
}
