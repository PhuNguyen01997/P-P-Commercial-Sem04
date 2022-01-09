package com.apt.p2p.repository;

import com.apt.p2p.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.user.id=:id ORDER BY o.id DESC")
    public List<Order> findAllByUserId(@Param("id") int userId);
}
