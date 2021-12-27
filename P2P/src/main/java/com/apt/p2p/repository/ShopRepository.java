package com.apt.p2p.repository;

import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Query("SELECT p.shop FROM Product p WHERE p.id = :id")
    Shop findByProductId(@Param("id") int productId);

////    @Query("SELECT c.product.shop, c.user FROM Cart c LEFT JOIN User u ON c.user.id = u.id WHERE u.id=:id")
//    @Query("SELECT s FROM Product p " +
//            "JOIN Cart c ON p.id = c.product.id " +
//            "JOIN Shop s ON p.shop.id = s.id " +
//            "JOIN User u ON u.id = c.id " +
//            "WHERE u.id=:id")
//    List<Shop> findHasCartByUserId(@Param("id") int userId);
}
