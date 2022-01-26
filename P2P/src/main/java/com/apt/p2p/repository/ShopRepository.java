package com.apt.p2p.repository;

import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query("SELECT p.shop FROM Product p WHERE p.id = :id")
    Shop findByProductId(@Param("id") int productId);

    @Query("SELECT p.shop FROM Product p WHERE p.id in :ids")
    List<Shop> findAllByProductId(@Param("ids") List<Integer> ids);

    @Query("SELECT o.shops FROM Order o WHERE o.id = :id")
    List<Shop> findByOrderId(@Param("id") int orderId);
}
