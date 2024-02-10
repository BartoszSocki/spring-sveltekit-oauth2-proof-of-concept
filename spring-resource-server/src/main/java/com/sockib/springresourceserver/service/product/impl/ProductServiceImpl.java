package com.sockib.springresourceserver.service.product.impl;

import com.sockib.springresourceserver.domain.product.factory.ProductSorterFactory;
import com.sockib.springresourceserver.domain.product.factory.ProductSpecificationFactory;
import com.sockib.springresourceserver.domain.product.query.ProductQueryCriteria;
import com.sockib.springresourceserver.exception.PageSizeTooLargeException;
import com.sockib.springresourceserver.model.dto.response.ProductResponseDto;
import com.sockib.springresourceserver.core.util.DtoConverter;
import com.sockib.springresourceserver.model.dto.converter.ProductDtoConverter;
import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;
import com.sockib.springresourceserver.model.respository.TagRepository;
import com.sockib.springresourceserver.model.respository.product.ProductRepository;
import com.sockib.springresourceserver.domain.product.factory.ProductFactory;
import com.sockib.springresourceserver.service.product.ProductCrudService;
import com.sockib.springresourceserver.service.product.ProductQueryService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductCrudService, ProductQueryService {

    private final static int MAX_PRODUCT_PAGE_SIZE = 10;
    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final DtoConverter<Product, ProductResponseDto> productConverter;

    public ProductServiceImpl(ProductRepository productRepository, TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.productConverter = new ProductDtoConverter();
    }

    @Override
    public List<ProductResponseDto> searchProduct(ProductQueryCriteria criteria, Pageable pageable) {
        if (pageable.getPageSize() > MAX_PRODUCT_PAGE_SIZE) {
            throw new PageSizeTooLargeException("page size: " + pageable.getPageSize() + " too large (>" + MAX_PRODUCT_PAGE_SIZE + ")");
        }

        var specification = ProductSpecificationFactory.create(criteria);
        var sorter = ProductSorterFactory.create(criteria);

        return productRepository.findProducts(specification, sorter, pageable)
                .stream()
                .map(productConverter::convert)
                .toList();
    }

    @Override
    @Transactional
    public Product saveProduct(AddProductRequestDto addProductRequestDto) {
        List<Tag> productTags = addProductRequestDto.getTags().stream().map(Tag::new).toList();
        tagRepository.saveAll(productTags);

        var product = ProductFactory.create(addProductRequestDto);

        var persistedProduct = productRepository.save(product);
        log.info("saved new product " + persistedProduct);
        return persistedProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteProductById(productId);
        log.info("deleted product with id: " + productId);
    }

}
