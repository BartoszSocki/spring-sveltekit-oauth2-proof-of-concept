package com.sockib.springresourceserver.service.review;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.converter.ProductReviewDtoConverter;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.model.embeddable.FiveStarScore;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.ProductReviewRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.model.respository.boughtproducts.BoughtProductRepository;
import com.sockib.springresourceserver.model.respository.products.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BoughtProductRepository boughtProductRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewDtoConverter productReviewDtoConverter;

    public ReviewServiceImpl(UserRepository userRepository,
                             ProductRepository productRepository,
                             BoughtProductRepository boughtProductRepository,
                             ProductReviewRepository productReviewRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.boughtProductRepository = boughtProductRepository;
        this.productReviewRepository = productReviewRepository;
        this.productReviewDtoConverter = new ProductReviewDtoConverter();
    }


    @Override
    public ProductReviewDto addReview(ReviewInputDto reviewInputDto, Long productId, String email) {
        var reviewer = userRepository.findUserByEmail(email).orElse(new User(email, new Money(1000.0, "USD")));
        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("TODO: add ProductNotFoundException"));

        throwIfUserDontHaveBoughtProduct(productId, reviewer.getId());

        var fiveStarScore = new FiveStarScore();
        fiveStarScore.setFiveStarScore(reviewInputDto.getFiveStarScore());

        var productReview = new ProductReview();
        productReview.setFiveStarScore(fiveStarScore);
        productReview.setReview(reviewInputDto.getReview());
        productReview.setReviewer(reviewer);
        productReview.setProduct(product);

        var savedProductReview = productReviewRepository.save(productReview);
        var productReviewDto = productReviewDtoConverter.convert(savedProductReview);

        return productReviewDto;
    }

    @Override
    public ProductReviewDto getProductReviewById(Long id) {
        var productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TODO: add product review not found"));

        var productReviewDto = productReviewDtoConverter.convert(productReview);

        return productReviewDto;
    }

    private void throwIfUserDontHaveBoughtProduct(Long productId, Long reviewerId) {
        var canReviewBeAdded = boughtProductRepository.existsBoughtProductByProductIdAndOwnerId(productId, reviewerId);
        if (!canReviewBeAdded) {
            throw new RuntimeException("TODO: add UserDontOwnProductException");
        }
    }

}
