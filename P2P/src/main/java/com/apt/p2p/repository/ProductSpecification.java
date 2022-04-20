package com.apt.p2p.repository;

import com.apt.p2p.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ProductSpecification {
    public static Specification<Product> hasShopId(int shopId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Product, Shop> joinProductShop = root.join("shop");
            return cb.equal(joinProductShop.get("id"), shopId);
        };
    }

    public static Specification<Product> hasId(String id) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.like(root.get("id").as(String.class), "%" + id + "%");
        };
    }

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            return cb.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<Product> hasCategoryId(int categoryId) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Product, Category> joinProductCategory = root.join("category");
            return cb.equal(joinProductCategory.get("id"), categoryId);
        };
    }

    public static Specification<Product> hasStock(boolean hasStock) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            if (hasStock) {
                return cb.notEqual(root.get("stock"), 0);
            } else {
                return cb.equal(root.get("stock"), 0);
            }
        };
    }

    public static Specification<Product> hasPrice(BigDecimal minPrice, BigDecimal maxPrice) {
//        Specification result = Specification.where(null);
//        return result
//                .and((root, query, cb) -> {
//                    root = joinAllRelation(root);
//                    return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
//                })
//                .and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            return cb.and(predicates.toArray(new Predicate[]{}));
        };
    }

    public static Specification<Product> hasRate(Integer rate) {
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Product, Rate> joinProductRate = root.join("rates");

            query.groupBy(root.get("id")).having(cb.greaterThanOrEqualTo(cb.avg(joinProductRate.get("star")), rate.doubleValue()));

            return query.getRestriction();
        };
    }

    public static Specification<Product> hasLocation(List<Integer> provinceIds){
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Product, Shop> joinProductShop = root.join("shop");
            Join<Shop, Address> joinShopAddress = joinProductShop.join("address");

            return joinShopAddress.get("provinceId").in(provinceIds);
        };
    }

    private static Root joinAllRelation(Root root) {
//        root.fetch("shop", JoinType.LEFT);
//        root.fetch("category", JoinType.LEFT);
        return root;
    }
}
