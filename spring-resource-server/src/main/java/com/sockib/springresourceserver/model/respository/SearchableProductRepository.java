package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.util.search.Page;
import com.sockib.springresourceserver.util.search.Sort;
import com.sockib.springresourceserver.util.search.Specification;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchableProductRepository {

    List<Product> findProducts(Specification<Product> specification, Page page, Sort sort, String entityGraphName);
    List<Product> findProducts(Specification<Product> specification, Page page, String entityGraphName);
    List<Product> findProducts(Specification<Product> specification, Page page);

}
