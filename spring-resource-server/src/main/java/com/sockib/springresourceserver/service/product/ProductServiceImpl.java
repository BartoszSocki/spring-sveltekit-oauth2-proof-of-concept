package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductInputDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.util.search.SearchFilter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable) {
        var specification = searchFilterToProductSpecificationConverter.convert(filters);

        return productRepository.findProducts(specification, Pageable.ofSize(10), "product[ForDisplay]");
    }

    @Override
    public List<Product> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort) {
        return null;
    }

    @Override
    public Product addNewProduct(ProductInputDto productInputDto, Authentication authentication) {
        // validation in dto
        // if all is good then save tags if they don't exist
        // create product entity
        // save
        return null;
    }

}
