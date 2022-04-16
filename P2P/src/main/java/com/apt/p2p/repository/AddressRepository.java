package com.apt.p2p.repository;

import com.apt.p2p.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a WHERE a.user.userId = :id ORDER BY a.id desc")
    List<Address> findAllByUserId(@Param("id") int userId);

    @Query("SELECT a FROM Address a WHERE a.shop.id=:id")
    Address findByShopId(@Param("id") int shopId);
}
