package com.apt.p2p.repository;

import com.apt.p2p.entity.*;
import com.apt.p2p.entity.ShopTransaction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public final class ShopTransactionSpecification {
    public static Specification<ShopTransaction> hasShopId(int shopId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<ShopTransaction, Shop> joinShop = root.join("shop");
            return cb.equal(joinShop.get("id"), shopId);
        };
    }

    public static Specification<ShopTransaction> hasDateIn(Date minDate, Date maxDate) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.between(root.get("date"), minDate, maxDate);
        };
    }

    public static Specification<ShopTransaction> hasOrder() {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.isNotNull(root.get("order"));
        };
    }

    public static Specification<ShopTransaction> hasNoOrder() {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.isNull(root.get("order"));
        };
    }

    private static Root joinAllRelation(Root root) {
        root.fetch("shop", JoinType.LEFT);
        root.fetch("order", JoinType.LEFT);
        return root;
    }
}
