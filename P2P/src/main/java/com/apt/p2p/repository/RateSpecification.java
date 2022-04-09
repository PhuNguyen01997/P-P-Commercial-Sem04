package com.apt.p2p.repository;

import com.apt.p2p.entity.Product;
import com.apt.p2p.entity.Rate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public final class RateSpecification {
    public static Specification<Rate> hasProductId(int productId){
        return (root, query, cb) -> {
            root = joinAllRelation(query, root);
            Join<Rate, Product> joinProductRate = root.join("product");
            return cb.equal(joinProductRate.get("id"), productId);
        };
    }

    private static Root joinAllRelation(CriteriaQuery query, Root root) {
        //This part allow to use this specification in pageable queries
        if(query.getResultType() != Long.class && query.getResultType() != long.class){
//        root.fetch("user", JoinType.LEFT);
//        root.fetch("product", JoinType.LEFT);
        }
        return root;
    }
}
