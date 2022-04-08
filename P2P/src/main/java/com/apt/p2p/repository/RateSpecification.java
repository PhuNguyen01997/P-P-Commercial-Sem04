package com.apt.p2p.repository;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public final class RateSpecification {
    public static Specification<Rate> hasProductId(int productId){
        return (root, query, cb) -> {
            root = joinAllRelation(root);
            Join<Rate, Product> joinProductRate = root.join("product");
            return cb.equal(joinProductRate.get("id"), productId);
        };
    }

    private static Root joinAllRelation(Root root) {
        root.fetch("user", JoinType.LEFT);
        root.fetch("product", JoinType.LEFT);
        return root;
    }
}
