package com.apt.p2p.repository;

import com.apt.p2p.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.product.id=:productId and c.user.userId=:userId")
    Cart findByProductIdAndUserId(@Param("productId") int productId, @Param("userId") int userId);

    @Query("SELECT c FROM Cart c " +
            "LEFT JOIN Product p ON c.product.id = p.id " +
            "LEFT JOIN Shop s ON p.shop.id = s.id " +
            "LEFT JOIN User u ON c.user.userId = u.userId " +
            "WHERE u.userId = :id " +
            "ORDER BY c.id")
    List<Cart> findAllByUserId(@Param("id") int userId);
}
