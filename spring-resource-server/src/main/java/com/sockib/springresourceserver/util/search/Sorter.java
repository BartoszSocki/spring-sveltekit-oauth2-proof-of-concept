package com.sockib.springresourceserver.util.search;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;

public interface Sorter<T> {

    Order toOrder(Path<T> path, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder);

}
