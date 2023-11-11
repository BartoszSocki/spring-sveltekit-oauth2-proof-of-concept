package com.sockib.springresourceserver.model.respository.products;

import com.sockib.springresourceserver.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, SearchableProductRepository {

    List<Product> findProductsByIdIn(List<Long> ids);

}