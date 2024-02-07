package com.sockib.springresourceserver.service.product.impl;

import com.sockib.springresourceserver.domain.product.factory.ProductSorterFactory;
import com.sockib.springresourceserver.domain.product.factory.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.exception.PageSizeTooLargeException;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import com.sockib.springresourceserver.model.dto.converter.DtoConverter;
import com.sockib.springresourceserver.model.dto.converter.ProductDtoConverter;
import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.respository.product.ProductRepository;
import com.sockib.springresourceserver.service.product.ProductFactory;
import com.sockib.springresourceserver.service.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final static int MAX_PRODUCT_PAGE_SIZE = 10;
    private final ProductRepository productRepository;
    private final DtoConverter<Product, ProductResponseDto> productConverter;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productConverter = new ProductDtoConverter();
    }

    @Override
    public List<ProductResponseDto> searchProduct(ProductQueryCriteria criteria, Pageable pageable) {
        if (pageable.getPageSize() > MAX_PRODUCT_PAGE_SIZE) {
            throw new PageSizeTooLargeException("page size: " + pageable.getPageSize() + " too large (>" + MAX_PRODUCT_PAGE_SIZE + ")");
        }

        var whereSpecification = ProductSpecificationFactory.where(criteria);
        var havingSpecification = ProductSpecificationFactory.having(criteria);
        var sorter = ProductSorterFactory.create(criteria);

        return productRepository.findProducts(whereSpecification, havingSpecification, sorter, pageable)
                .stream()
                .map(productConverter::convert)
                .toList();
    }

    @Override
    @Transactional
    public Product addNewProduct(AddProductRequestDto addProductRequestDto) {
        var product = ProductFactory.create(addProductRequestDto);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteProductById(productId);
    }

}
