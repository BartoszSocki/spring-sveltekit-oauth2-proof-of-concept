package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.util.search.SearchFilter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
    }

    @Override
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable) {

        var specification = searchFilterToProductSpecificationConverter.convert(filters);

        return null;
    }

    @Override
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort) {
        return null;
    }

}
