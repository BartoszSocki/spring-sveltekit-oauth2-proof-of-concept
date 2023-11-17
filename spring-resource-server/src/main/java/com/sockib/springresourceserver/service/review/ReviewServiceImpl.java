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
import com.sockib.springresourceserver.model.respository.products.ProductRepository;
import com.sockib.springresourceserver.service.boughtproduct.BoughtProductService;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final BoughtProductService boughtProductService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ProductReviewDtoConverter productReviewDtoConverter;

    public ReviewServiceImpl(BoughtProductService boughtProductService,
                             UserRepository userRepository,
                             ProductRepository productRepository,
                             ProductReviewRepository productReviewRepository) {
        this.boughtProductService = boughtProductService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
        this.productReviewDtoConverter = new ProductReviewDtoConverter();
    }

    @Override
    public ProductReviewDto addReview(ReviewInputDto reviewInputDto, Long productId, String email) {
        var reviewer = userRepository.findUserByEmail(email).orElse(new User(email, new Money(1000.0, "USD")));
        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("TODO: add ProductNotFoundException"));

        if (!isUserOwningProduct(reviewer.getId(), productId)) {
            throw new RuntimeException("TODO: add UserDontOwnProductException");
        }

        if (!hasUserAlreadyAddedReviewForProduct(reviewer.getId(), productId)) {
            throw new RuntimeException("TODO: add UserAlreadyAddedReviewException");
        }

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

    private boolean isUserOwningProduct(Long reviewerId, Long productId) {
        return boughtProductService.isUserOwnerOfBoughtProduct(reviewerId, productId);
    }

    private boolean hasUserAlreadyAddedReviewForProduct(Long reviewerId, Long productId) {
        return productReviewRepository.hasUserAddedReviewForProduct(reviewerId, productId);
    }

}
