package com.apt.p2p.repository;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.Shop;
import com.apt.p2p.entity.User;
import groovyjarjarpicocli.CommandLine;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public final class RateSpecification {
    public static Specification<Rate> hasShopId(int shopId) {
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            Join<Rate, Product> joinProductRate = root.join("product");
            Join<Product, Shop> joinProductShop = joinProductRate.join("shop");
            return cb.equal(joinProductShop.get("id"), shopId);
        };
    }

    public static Specification<Rate> hasProductId(int productId) {
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            Join<Rate, Product> joinProductRate = root.join("product");
            return cb.equal(joinProductRate.get("id"), productId);
        };
    }

    public static Specification<Rate> hasProductName(String productName) {
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            Join<Rate, Product> joinProductRate = root.join("product");
            return cb.like(joinProductRate.get("name"), "%" + productName + "%");
        };
    }

    public static Specification<Rate> hasDateIn(Date minDate, Date maxDate) {
        LocalDateTime ldtMaxDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(maxDate.getTime()), ZoneOffset.UTC);
        ldtMaxDate = ldtMaxDate.plusDays(1);
        Date finalMaxDate = Date.from(ldtMaxDate.toInstant(ZoneOffset.UTC));

        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            return cb.between(root.get("createdAt"), minDate, finalMaxDate);
        };
    }

    public static Specification<Rate> hasUsername(String username) {
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            Join<Rate, User> joinRateUser = root.join("user");
            return cb.like(joinRateUser.get("username"), "%" + username + "%");
        };
    }

    public static Specification<Rate> hasStar(int star) {
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            return cb.equal(root.get("star"), star);
        };
    }

    private static Root joinAllRelation(CriteriaQuery query, Root root) {
        //This part allow to use this specification in pageable queries
        if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//        root.fetch("user", JoinType.LEFT);
//        root.fetch("product", JoinType.LEFT);
//        root.fetch("order", JoinType.LEFT);
        }
        return root;
    }
}
