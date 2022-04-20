package com.apt.p2p.repository;

import com.apt.p2p.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Integer>, JpaSpecificationExecutor {
    @Query("SELECT p.rates FROM Product p WHERE p.id=:id")
    List<Rate> findByProductId(@Param("id") int productId);

    @Query("SELECT COUNT(r) FROM Rate r WHERE r.product.shop.id=:id")
    Integer countByShopId(@Param("id") int shopId);

    @Query("SELECT r FROM Rate r " +
            "JOIN Order o ON r.order.id = o.id " +
            "WHERE o.id = :id")
    List<Rate> findAllByOrderId(@Param("id") int orderId);
}
