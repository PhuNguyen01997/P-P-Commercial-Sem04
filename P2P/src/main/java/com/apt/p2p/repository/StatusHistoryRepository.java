package com.apt.p2p.repository;

import com.apt.p2p.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {
    @Query("SELECT sh FROM StatusHistory sh WHERE sh.order.id = :orderId AND sh.status.id = :statusId")
    StatusHistory findByOrderIdAndStatusId(@Param("orderId") int orderId, @Param("statusId") int statusId);
}
