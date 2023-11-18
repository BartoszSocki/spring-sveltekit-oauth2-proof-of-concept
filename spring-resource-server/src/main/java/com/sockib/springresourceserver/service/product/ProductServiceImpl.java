package com.sockib.springresourceserver.service.product;

import com.sockib.springresourceserver.model.dto.ProductDto;
import com.sockib.springresourceserver.model.dto.converter.ToProductDtoConverter;
import com.sockib.springresourceserver.model.dto.converter.ToDtoConverter;
import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.*;
import com.sockib.springresourceserver.model.respository.CategoryRepository;
import com.sockib.springresourceserver.model.respository.products.ProductRepository;
import com.sockib.springresourceserver.model.respository.TagRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.util.search.filter.SearchFilter;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.page.SimplePageImpl;
import com.sockib.springresourceserver.util.search.sort.Sort;
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
    private final ToDtoConverter<Product, ProductDto> productConverter;
    private final static Integer MAX_PRODUCT_PAGE_SIZE = 10;


    public ProductServiceImpl(ProductRepository productRepository, TagRepository tagRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.searchFilterToProductSpecificationConverter = new SearchFilterToProductSpecificationConverterImpl();
        this.sortToProductSorterConverter = new SortToProductSorterConverterImpl();
        this.categoryRepository = categoryRepository;
        this.productConverter = new ToProductDtoConverter();
    }

    @Override
    public SimplePage<ProductDto> searchProduct(List<SearchFilter> filters,
                                                Pageable pageable,
                                                Sort sort) {
        if (pageable.getLimit() > MAX_PRODUCT_PAGE_SIZE) {
            throw new RuntimeException("TODO: implement max page limit exceeded");
        }

        var specification = searchFilterToProductSpecificationConverter.convert(filters);
        var sorter = sortToProductSorterConverter.convert(sort);
        var productsPage = productRepository.findProducts(specification, pageable, sorter);

        var products = productsPage.getContent()
                .stream()
                .map(productConverter::convert)
                .toList();

        var page = new SimplePageImpl<ProductDto>(
                products,
                productsPage.isFirstPage(),
                productsPage.isLastPage()
        );

        return page;
    }

    @Override
    public List<ProductDto> findProductsByIds(List<Long> ids) {
        var products = productRepository.findProductsWithIdInFetchAllColumns(ids);
        var productsDtos = products.stream()
                .map(productConverter::convert)
                .toList();

        return productsDtos;
    }

    @Override
    @Transactional
    public Product addNewProduct(@Valid ProductInput productInput, String email) {
        var owner = userRepository.findUserByEmail(email)
                .orElse(new User(email, new Money(0.0, "USD")));

        var inputTags = productInput.getTags();
        var existingTags = tagRepository.findAllByNameIn(inputTags);
        var tags = combineExistingTagsWithNewTags(inputTags, existingTags);
        tagRepository.saveAll(tags);

        var productCategory = categoryRepository.findCategoryByName(productInput.getCategory())
                .orElse(new Category(productInput.getCategory()));

        if (!"USD".equals(productInput.getPrice().getCurrency())) {
            throw new RuntimeException("TODO: add CurrencyNotSupportedException");
        }

        var money = new Money();
        money.setAmount(productInput.getPrice().getAmount());
        money.setCurrency(productInput.getPrice().getCurrency());

        var inventory = new ProductInventory();
        inventory.setQuantity(productInput.getQuantity());

        var newProduct = new Product();
        newProduct.setName(productInput.getName());
        newProduct.setDescription(productInput.getDescription());
        newProduct.setCategory(productCategory);
        newProduct.setTags(tags);
        newProduct.setOwner(owner);
        newProduct.setInventory(inventory);
        newProduct.setPrice(money);

        var product = productRepository.save(newProduct);
        return product;
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
