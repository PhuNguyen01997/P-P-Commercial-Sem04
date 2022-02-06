package com.apt.p2p.repository;

import com.apt.p2p.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor {
    @Query("SELECT o FROM Order o WHERE o.user.id=:id ORDER BY o.id DESC")
    List<Order> findAllByUserId(@Param("id") int userId);

    @Query("SELECT o FROM Order o WHERE o.shop.id=:id ORDER BY o.id DESC")
    List<Order> findAllByShopId(@Param("id") int shopId);

//    @Query("SELECT o FROM Order o WHERE o.st")
//    List<Order> findAllWithFilter();

    @Override
    @Query("SELECT o FROM Order o" +
            " JOIN OrderStatusOrder oso ON oso.order.id = o.id" +
            " JOIN StatusOrder s ON oso.status.id = s.id" +
            " WHERE o.id = :id")
    Optional<Order> findById(@Param("id") Integer id);
}
