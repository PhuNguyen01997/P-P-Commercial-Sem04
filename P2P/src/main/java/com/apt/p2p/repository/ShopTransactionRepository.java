package com.apt.p2p.repository;

import com.apt.p2p.entity.ShopTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopTransactionRepository extends JpaRepository<ShopTransaction, Integer> {
}
