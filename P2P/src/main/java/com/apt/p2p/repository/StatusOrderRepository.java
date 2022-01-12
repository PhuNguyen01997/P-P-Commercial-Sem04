package com.apt.p2p.repository;

import com.apt.p2p.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer> {
}
