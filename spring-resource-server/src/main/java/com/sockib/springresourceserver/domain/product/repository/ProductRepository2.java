package com.sockib.springresourceserver.domain.product.repository;

import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository2 extends JpaRepository<Product, Long>, SearchableProductRepository2 {

    List<Product> findProductsByIdIn(List<Long> ids);

    @Query("select p from Product p where p.id in :ids")
    List<Product> findProductsWithIdInFetchAllColumns(List<Long> ids);

    @Modifying
    @Query("update Product p set p.isDeleted = true where p.id = :id and p.owner.email = :email")
    void softDeleteProductByIdAndOwnerEmail(Long id, String email);

}