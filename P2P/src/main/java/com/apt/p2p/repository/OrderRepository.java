package com.apt.p2p.repository;

import com.apt.p2p.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.user.id=:id ORDER BY o.id DESC")
    List<Order> findAllByUserId(@Param("id") int userId);

    @Override
    @Query("SELECT o FROM Order o" +
            " INNER JOIN StatusOrder s" +
            " WHERE o.id = :id")
    Optional<Order> findById(@Param("id") Integer id);
}
