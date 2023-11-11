package com.sockib.springresourceserver.model.respository.boughtproducts;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.model.entity.BoughtProduct_;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.User_;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.page.SimplePageImpl;
import com.sockib.springresourceserver.util.search.sort.Sorter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SearchableBoughtProductRepositoryImpl implements SearchableBoughtProductRepository {

    private final EntityManager entityManager;

    @Override
    public SimplePage<BoughtProduct> searchBoughtProducts(Pageable pageable, Sorter<BoughtProduct> sorter) {
        var buyerId = 1L;
        var boughtProducts = getBoughtProducts(pageable, sorter, buyerId);
        var count = getNumberOfBoughtProducts(buyerId);

        var isFirstPage = pageable.getPage() == 0;
        var isLastPage = count <= pageable.getOffset() + pageable.getLimit();

        return new SimplePageImpl<>(boughtProducts, isFirstPage, isLastPage);
    }

    private List<BoughtProduct> getBoughtProducts(Pageable pageable, Sorter<BoughtProduct> sorter, Long buyerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BoughtProduct> criteriaQuery = criteriaBuilder.createQuery(BoughtProduct.class);
        Root<BoughtProduct> root = criteriaQuery.from(BoughtProduct.class);

        var order = sorter.toOrder(root, criteriaQuery, criteriaBuilder);

        var query = criteriaQuery
                .where(criteriaBuilder.equal(root.get(BoughtProduct_.OWNER).get(User_.ID), buyerId))
                .orderBy(order);

        var list = entityManager.createQuery(query)
                .setFirstResult(pageable.getOffset().intValue())
                .setMaxResults(pageable.getLimit().intValue())
                .getResultList();

        return list;
    }

    private Long getNumberOfBoughtProducts(Long buyerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        var query = criteriaQuery
                .select(criteriaBuilder.countDistinct(root.get(BoughtProduct_.ID)))
                .where(criteriaBuilder.equal(root.get(BoughtProduct_.OWNER).get(User_.ID), buyerId));

        var count = entityManager.createQuery(query)
                .getSingleResult();

        return count;
    }

}
