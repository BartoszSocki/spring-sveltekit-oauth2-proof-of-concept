package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.model.dto.TagDto;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.*;
import com.sockib.springresourceserver.model.respository.ProductRepository;
import com.sockib.springresourceserver.model.respository.TagRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.util.search.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final SearchFilterToProductSpecificationConverter searchFilterToProductSpecificationConverter;
    private final SortToProductSorterConverter sortToProductSorterConverter;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, TagRepository tagRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
        this.sortToProductSorterConverter = new SortToProductSorterConverterImpl();

        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductDto> searchProduct(List<SearchFilter> filters, Pageable pageable, Sort sort) {
        var specification = searchFilterToProductSpecificationConverter.convert(filters);
        var sorter = sortToProductSorterConverter.convert(sort);

        var page = productRepository.findProducts(specification, pageable, sorter);
        var products = page.getContent()
                .stream()
                .map(ProductDto::new)
                .toList();

        return new PageImpl<>(products, page.getPage(), page.getPageSize(), page.getTotal());
    }

    @Override
    public List<ProductDto> findProductsByIds(List<Long> ids) {
        var products = productRepository.findProductsByIdIn(ids);
        return products.stream()
                .map(ProductDto::new)
                .toList();
    }

    @Override
    @Transactional
    public Product addNewProduct(@Valid ProductInput productInput, String email) {
        var user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("TODO: add User Not Found Exception"));

        var tagNames = productInput.getTags().stream().map(TagDto::getName).toList();
        List<Tag> existingTags = tagRepository.findAllByNameIn(tagNames);
        var productTags = combineExistingTagsWithNewTags(tagNames, existingTags);

        var productCategory = categoryRepository.findCategoryByName(productInput.getCategory())
                .orElse(new Category(productInput.getCategory()));

        var money = new Money();
        money.setAmount(productInput.getPrice().getAmount());
        money.setCurrency(productInput.getPrice().getCurrency());

        var product = Product.builder()
                .name(productInput.getName())
                .category(productCategory)
                .tags(productTags)
                .description(productInput.getDescription())
                .imageUrl(productInput.getImageUrl())
                .owner(user)
                .inventory(new ProductInventory(productInput.getQuantity()))
                .price(money)
                .build();

        return productRepository.save(product);
    }

    private List<Tag> combineExistingTagsWithNewTags(List<String> allTagNames, List<Tag> existingTags) {
        Set<String> alreadyExistingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = allTagNames.stream()
                .filter(t -> !alreadyExistingTagNames.contains(t))
                .map(Tag::new)
                .toList();

        return Stream.concat(existingTags.stream(), newTags.stream()).toList();
    }

}
