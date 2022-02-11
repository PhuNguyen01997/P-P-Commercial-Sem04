package com.apt.p2p.repository;

import com.apt.p2p.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusOrderRepository extends JpaRepository<StatusHistory, Integer> {
}
