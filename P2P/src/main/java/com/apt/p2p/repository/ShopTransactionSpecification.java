package com.apt.p2p.repository;

import com.apt.p2p.entity.*;
import com.apt.p2p.entity.ShopTransaction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public final class ShopTransactionSpecification {
    public static Specification<ShopTransaction> hasShopId(int shopId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            query.orderBy(cb.desc(root.get("id")));

            Join<ShopTransaction, Shop> joinShop = root.join("shop");
            return cb.equal(joinShop.get("id"), shopId);
        };
    }

    public static Specification<ShopTransaction> hasDateIn(Date minDate, Date maxDate) {
        LocalDateTime ldtMaxDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(maxDate.getTime()), ZoneOffset.UTC);
        ldtMaxDate = ldtMaxDate.plusDays(1);
        Date finalMaxDate = Date.from(ldtMaxDate.toInstant(ZoneOffset.UTC));

        return (root, query, cb) -> {
            root = joinAllRelation(root);

            return cb.between(root.get("date"), minDate, finalMaxDate);
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
