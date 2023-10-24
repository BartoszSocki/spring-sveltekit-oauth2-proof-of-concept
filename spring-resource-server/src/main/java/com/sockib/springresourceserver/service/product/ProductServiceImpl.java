package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.util.search.SearchFilter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final EntityManagerFactory entityManagerFactory;

    @SuppressWarnings("unchecked")
    public List<Product> searchProduct(List<SearchFilter> filters) {
        var specification = ProductSearch.resolve(filters);

        var em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        var query = criteriaQuery.where(predicate);

        return (List<Product>) em.createQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable) {
        var specification = ProductSearch.resolve(filters);

        var em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();
        Root<Product> root = criteriaQuery.from(Product.class);

        var predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        var query = criteriaQuery.where(predicate);

        return (List<Product>) em.createQuery(query).getResultList();
    }

    @Override
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort) {
        return null;
    }

}
