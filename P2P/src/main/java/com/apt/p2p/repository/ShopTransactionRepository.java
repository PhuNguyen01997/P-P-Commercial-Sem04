package com.apt.p2p.repository;

import com.apt.p2p.entity.ShopTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopTransactionRepository extends JpaRepository<ShopTransaction, Integer>, JpaSpecificationExecutor {
    @Query("SELECT st FROM ShopTransaction st ORDER BY st.status ASC, st.id DESC")
    List<ShopTransaction> findAll();
}
