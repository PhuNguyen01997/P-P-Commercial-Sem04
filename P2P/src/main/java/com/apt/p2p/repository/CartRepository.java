package com.apt.p2p.repository;

import com.apt.p2p.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.product.id=:productId and c.user.id=:userId")
    public Cart findByProductIdAndUserId(@Param("productId") int productId, @Param("userId") int userId);

//    @Query("SELECT c FROM Cart c WHERE c.product.shop.id=:shopId and c.user.id=:userId")
//    public Cart findByShopIdAndUserId(@Param("shopId") int shopId, @Param("userId") int userId);

    @Query("SELECT c FROM Cart c WHERE c.user.id=:id")
    public List<Cart> findByUserId(@Param("id") int userId);
}
