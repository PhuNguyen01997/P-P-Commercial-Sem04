package com.apt.p2p.repository;

import com.apt.p2p.entity.ShopFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopFundRepository extends JpaRepository<ShopFund, Integer> {
//    @Query("SELECT od FROM ShopFund od WHERE od.user.id = :userId AND od.month = :month AND od.year = :year")
//    public Optional<ShopFund> findByUserIdAndMonth(@Param("userId") int userId, @Param("month") int month, @Param("year") int year);

    @Query("SELECT sf from ShopFund sf WHERE sf.shop.id = :id")
    Optional<ShopFund> findByShopId(@Param("id") int shopId);
}
