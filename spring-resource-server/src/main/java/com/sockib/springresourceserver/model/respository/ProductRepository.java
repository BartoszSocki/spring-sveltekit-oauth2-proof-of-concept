package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, SearchableProductRepository {

    List<Product> findProductsByIdIn(List<Long> ids);

}