package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductInputDto;
import com.sockib.springresourceserver.model.dto.TagDto;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.*;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.model.respository.TagRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.util.search.SearchFilter;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, TagRepository tagRepository, UserRepository userRepository, ModelMapper modelMapper,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
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
    public Product addNewProduct(@Valid ProductInputDto productInputDto, Authentication authentication) {
        var email = authentication.getName();
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("TODO: add User Not Found Exception"));

        var tags = productInputDto.getTags().stream().map(TagDto::getName).toList();
        var fetchedTags = tagRepository.findAllByNameIn(tags);
        var fetchedTagsNames = fetchedTags.map(Tag::getName).collect(Collectors.toSet());
        var newTags = tags.stream().filter(t -> !fetchedTagsNames.contains(t)).map(Tag::new);

        var productTags = Stream.concat(newTags, fetchedTags).toList();

        var productCategory = categoryRepository.findCategoryByName(productInputDto.getCategory())
                .orElse(new Category(productInputDto.getCategory()));

        var product = Product.builder()
                .name(productInputDto.getName())
                .category(productCategory)
                .tags(productTags)
                .description(productInputDto.getDescription())
                .imageUrl(productInputDto.getImageUrl())
                .owner(user)
                .inventory(new ProductInventory(productInputDto.getQuantity()))
                .price(productInputDto.getPrice().toMoney())
                .build();

        return productRepository.save(product);
    }

}
