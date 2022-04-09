package com.apt.p2p.repository;

import com.apt.p2p.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT ode FROM OrderDetail ode WHERE ode.order.id = :id")
    List<OrderDetail> findAllByOrderId(@Param("id") int orderId);
}
