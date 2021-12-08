package com.apt.p2p.repository;

import com.apt.p2p.entity.Address;
import com.apt.p2p.repository.Custom.AddressRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {
    Address findById(Integer id);

    void deleteAddressById(Integer id);
}
