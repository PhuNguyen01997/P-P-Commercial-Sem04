package com.apt.p2p.repository;

import com.apt.p2p.entity.*;
import com.apt.p2p.entity.Order;
import com.apt.p2p.model.form.FilterOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public final class OrderSpecification {
    public static Specification<Order> hasShopId(int shopId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Order, Shop> joinShop = root.join("shop");
            return cb.equal(joinShop.get("id"), shopId);
        };
    }

    public static Specification<Order> hasStatusId(int statusId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Order, StatusOrder> joinOrderStatusOrder = root.join("currentStatus");
            return cb.equal(joinOrderStatusOrder.get("id"), statusId);
        };
    }

    public static Specification<Order> hasDateIn(Date minDate, Date maxDate) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.between(root.get("createdAt"), minDate, maxDate);
        };
    }

    public static Specification<Order> likeNameByType(int typeId, String name) {
        Specification<Order> result = null;
        switch (typeId) {
            case 1: {
//                Filter By OrderId
                result = (root, query, cb) -> {
                    return cb.like(root.get("id").as(String.class), "%" + name + "%");
                };
                break;
            }
            case 2: {
//                Filter By User Name
                result = (root, query, cb) -> {
                    Join<Order, User> joinUser = root.join("user");
                    return cb.like(joinUser.get("username"), "%" + name + "%");
                };
                break;
            }
            case 3: {
//                Filter By Product Name
                result = (root, query, cb) -> {
                    query.distinct(true);

                    Join<Order, OrderDetail> joinOrderDetails = root.join("orderDetails");
                    Join<OrderDetail, Product> joinProduct = joinOrderDetails.join("product");
                    return cb.like(joinProduct.get("name"), "%" + name + "%");
                };
                break;
            }
        }
        return result;
    }

    private static Root joinAllRelation(Root root) {
//        root.fetch("shop", JoinType.LEFT);
//        root.fetch("user", JoinType.LEFT);
//        root.fetch("address", JoinType.LEFT);
//        root.fetch("currentStatus", JoinType.LEFT);
        return root;
    }
}
