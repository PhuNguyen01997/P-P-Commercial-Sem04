package com.apt.p2p.repository;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.Shop;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT s.products FROM Shop s WHERE s.id = :id")
    public List<Product> findByShopId(@Param("id") int shopId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.shop.id = :id")
    public Integer countByShopId(@Param("id") int shopId);

    @Query("SELECT c.product FROM Cart c WHERE c.id=:id")
    public Product findByCartId(@Param("id") int cartId);
}
