package com.sockib.springresourceserver.model.entity;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import com.sockib.springresourceserver.model.valueobject.Price;
import com.sockib.springresourceserver.model.repository.ProductDetailsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

// TODO: connect to test db

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductDetailsTest {

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Test
    void givenProductDetailsWithDescriptionAndPrice_whenPersisting_thenSuccess() {
        // given
        var price = new Price();
        price.setPrice(120.0D);
        price.setCurrency("PLN");

        var productDetails = new ProductDetails();
        productDetails.setName("name");
        productDetails.setDescription("description");
        productDetails.setPrice(price);

        // when
        var persistedProductRepository = productDetailsRepository.save(productDetails);

        // then
        assert true;
    }

    @Test
    void givenProductDetailsWithDescriptionAndPriceAndTags_whenPersisting_thenSuccess() {
        // given
        var price = new Price();
        price.setPrice(120.0D);
        price.setCurrency("PLN");

        var tags = new HashSet<Tag>();
        var tag1 = new Tag("tag1");
        var tag2 = new Tag("tag2");

        tags.add(tag1);
        tags.add(tag2);

        final String description = "desc";
        var productDetails = new ProductDetails();
        productDetails.setName("name");
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setTags(tags);

        // when
        var persistedProductDetails = productDetailsRepository.save(productDetails);

        // then
        var retrievedProductDetails = productDetailsRepository.findById(persistedProductDetails.getId());

        assertThat(retrievedProductDetails).isPresent();
        assertThat(retrievedProductDetails.get().getDescription()).isEqualTo(description);
        assertThat(retrievedProductDetails.get().getPrice()).isEqualTo(price);
        assertThat(retrievedProductDetails.get().getTags()).hasSize(tags.size());
        assertThat(retrievedProductDetails.get().getTags()).doesNotContainNull();
        assertThat(retrievedProductDetails.get().getTags().stream().map(Tag::getName).collect(Collectors.toSet())).contains("tag1", "tag2");
    }
    @Test
    void givenProductDetailsWithDescriptionAndPriceAndTagsAndCategories_whenPersisting_thenSuccess() {
        // given
        var price = new Price();
        price.setPrice(120.0D);
        price.setCurrency("PLN");

        var categories = new HashSet<Category>();
        var cat1 = new Category("cat1");
        categories.add(cat1);

        var tags = new HashSet<Tag>();
        var tag1 = new Tag("tag1");
        tags.add(tag1);

        final String description = "desc";
        var productDetails = new ProductDetails();
        productDetails.setName("name");
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setCategories(categories);
        productDetails.setTags(tags);

        // when
        var persistedProductDetails = productDetailsRepository.save(productDetails);

        // then
        var retrievedProductDetails = productDetailsRepository.findById(persistedProductDetails.getId());

        assertThat(retrievedProductDetails).isPresent();
        assertThat(retrievedProductDetails.get().getCategories().stream().map(Resource::getCreationDate).collect(Collectors.toList())).doesNotContainNull();
        assertThat(retrievedProductDetails.get().getTags().stream().map(Resource::getCreationDate).collect(Collectors.toList())).doesNotContainNull();
        assertThat(retrievedProductDetails.get().getDescription()).isEqualTo(description);
        assertThat(retrievedProductDetails.get().getPrice()).isEqualTo(price);
        assertThat(retrievedProductDetails.get().getCategories()).hasSize(categories.size());
        assertThat(retrievedProductDetails.get().getTags()).hasSize(categories.size());
        assertThat(retrievedProductDetails.get().getCategories()).doesNotContainNull();
        assertThat(retrievedProductDetails.get().getTags()).doesNotContainNull();
        assertThat(retrievedProductDetails.get().getCategories().stream().map(Category::getName).collect(Collectors.toSet())).contains("cat1");
        assertThat(retrievedProductDetails.get().getTags().stream().map(Tag::getName).collect(Collectors.toSet())).contains("tag1");
    }

    @Test
    void givenProductDetailsWithDuplicateCategories_whenPersist_thenError() {
        // given
        var price = new Price();
        price.setPrice(120.0D);
        price.setCurrency("PLN");

        var categories = new HashSet<Category>();
        var cat1 = new Category("cat1");
        var cat2 = new Category("cat1");

        categories.add(cat1);
        categories.add(cat2);

        final String description = "desc";
        var productDetails = new ProductDetails();
        productDetails.setName("name");
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setCategories(categories);

        // then
        assertThatThrownBy(() -> {
            // when
            productDetailsRepository.save(productDetails);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void givenProductDetailsWithDuplicateTags_whenPersist_thenError() {
        // given
        var price = new Price();
        price.setPrice(120.0D);
        price.setCurrency("PLN");

        var tags = new HashSet<Tag>();
        var tag1 = new Tag("tag1");
        var tag2 = new Tag("tag1");

        tags.add(tag1);
        tags.add(tag2);

        final String description = "desc";
        var productDetails = new ProductDetails();
        productDetails.setName("name");
        productDetails.setDescription(description);
        productDetails.setPrice(price);
        productDetails.setTags(tags);

        // then
        assertThatThrownBy(() -> {
            // when
            productDetailsRepository.save(productDetails);
        }).isInstanceOf(RuntimeException.class);
    }
}
