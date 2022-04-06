package com.apt.p2p.repository;

import com.apt.p2p.entity.Category;
import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import com.apt.p2p.entity.Shop;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

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
        Specification<Product> result = Specification.where(null);
        if (minPrice != null) {
            result = result.and((root, query, cb) -> {
                root = joinAllRelation(root);
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            });
        }
        if (maxPrice != null) {
            result = result.and((root, query, cb) -> {
                root = joinAllRelation(root);
                return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
            });
        }
        return result;
    }

    public static Specification<Product> hasRate(Integer rate) {
        return (root, query, cb) -> {

            root = joinAllRelation(root);
            Join<Product, Rate> joinProductRate = root.join("rates");
//            cb.avg(joinProductRate.get("star")).;
            return cb.greaterThanOrEqualTo(joinProductRate.get("star"), rate);
        };
    }

    private static Root joinAllRelation(Root root) {
        root.fetch("shop", JoinType.LEFT);
        root.fetch("category", JoinType.LEFT);
        return root;
    }
}
