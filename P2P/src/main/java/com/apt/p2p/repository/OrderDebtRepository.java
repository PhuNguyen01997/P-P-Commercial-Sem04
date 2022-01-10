package com.apt.p2p.repository;

import com.apt.p2p.entity.OrderDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderDebtRepository extends JpaRepository<OrderDebt, Integer> {
    @Query("SELECT od FROM OrderDebt od WHERE od.user.id = :userId AND od.month = :month AND od.year = :year")
    public Optional<OrderDebt> findByUserIdAndMonth(@Param("userId") int userId, @Param("month") int month, @Param("year") int year);
}
