package com.sockib.springresourceserver.model.respository.products;

import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, SearchableProductRepository {

    @EntityGraph(value = "product[inventory]")
    List<Product> findProductsByIdIn(List<Long> ids);

    @EntityGraph(value = "product[all]")
    @Query("select p from Product p where p.id in :ids")
    List<Product> findProductsWithIdInFetchAllColumns(List<Long> ids);

}