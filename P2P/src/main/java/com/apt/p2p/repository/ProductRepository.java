package com.apt.p2p.repository;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p.shop FROM Product p WHERE p.id = :id")
    public Shop findShopByProductId(@Param("id") int productId);

    @Query("SELECT p.shop.rates FROM Product p WHERE p.id = :id")
    public List<Rate> findRatesByProductId(@Param("id") int productId);
}
