package com.apt.p2p.repository;

import com.apt.p2p.entity.ShopTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopTransactionRepository extends JpaRepository<ShopTransaction, Integer>, JpaSpecificationExecutor {
}
