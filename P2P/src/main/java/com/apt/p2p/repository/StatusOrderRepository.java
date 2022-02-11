package com.apt.p2p.repository;

import com.apt.p2p.entity.Order;
import com.apt.p2p.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer> {
    @Query("SELECT sh.status FROM StatusHistory sh WHERE sh.id = :id")
    StatusOrder findByStatusHistory(@Param("id") int statusHistoryId);

    @Query("SELECT sh.status FROM StatusHistory sh WHERE sh.id = :id")
    StatusOrder findByStatusHistoryId(@Param("id") int statusHistoryId);
}
