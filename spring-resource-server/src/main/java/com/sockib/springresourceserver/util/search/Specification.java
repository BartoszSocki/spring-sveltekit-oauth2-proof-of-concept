package com.sockib.springresourceserver.util.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public interface Specification<T> {

    Predicate toPredicate(Path<T> path, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder);

    static <T> Specification<T> and(Specification<T> spec1, Specification<T> spec2) {
        return ((path, criteriaQuery, criteriaBuilder) -> {
            var p1 = spec1.toPredicate(path, criteriaQuery, criteriaBuilder);
            var p2 = spec2.toPredicate(path, criteriaQuery, criteriaBuilder);

            return criteriaBuilder.and(p1, p2);
        });
    }

}
