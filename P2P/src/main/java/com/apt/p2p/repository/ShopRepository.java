package com.apt.p2p.repository;

import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query("SELECT p.shop FROM Product p WHERE p.id = :id")
    public Shop findByProductId(@Param("id") int productId);

//    @Query("SELECT c.product.shop FROM Cart c WHERE c.user.id=:userId")
//    public List<Shop> findHasCartByUserId(@Param("id") int userId);
}
