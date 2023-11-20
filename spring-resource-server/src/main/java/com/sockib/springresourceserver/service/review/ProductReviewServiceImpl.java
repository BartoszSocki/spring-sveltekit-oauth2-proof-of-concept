package com.sockib.springresourceserver.service.review;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.converter.ToProductReviewDtoConverter;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.model.embeddable.FiveStarScore;
import com.sockib.springresourceserver.model.entity.ProductReview;
import com.sockib.springresourceserver.model.exception.ProductReviewNotFoundException;
import com.sockib.springresourceserver.model.respository.ProductReviewRepository;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.model.respository.product.ProductRepository;
import com.sockib.springresourceserver.service.boughtproduct.BoughtProductService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    private final BoughtProductService boughtProductService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;
    private final ToProductReviewDtoConverter productReviewConverter;

    public ProductReviewServiceImpl(BoughtProductService boughtProductService,
                                    UserRepository userRepository,
                                    ProductRepository productRepository,
                                    ProductReviewRepository productReviewRepository) {
        this.boughtProductService = boughtProductService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productReviewRepository = productReviewRepository;
        this.productReviewConverter = new ToProductReviewDtoConverter();
    }

    @Override
    public void putReview(ReviewInputDto reviewInputDto, Long productId, String email) {
//        var reviewer = userRepository.findUserByEmail(email).orElse(new User(email, new Money(1000.0, "USD")));
        var reviewer = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user with email: " + email + " not found"));

        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("TODO: add ProductNotFoundException"));

        if (!isUserOwningProduct(reviewer.getId(), productId)) {
            throw new RuntimeException("TODO: add UserDontOwnProductException");
        }

        var fiveStarScore = new FiveStarScore();
        fiveStarScore.setFiveStarScore(reviewInputDto.getFiveStarScore());

        var productReview = productReviewRepository.findByProductIdAndOwnerEmail(productId, email).orElse(new ProductReview());
        productReview.setFiveStarScore(fiveStarScore);
        productReview.setReview(reviewInputDto.getReview());
        productReview.setReviewer(reviewer);
        productReview.setProduct(product);

        productReviewRepository.save(productReview);
    }

    @Override
    public ProductReviewDto getProductReviewById(Long id) {
        var productReview = productReviewRepository.findById(id)
                .orElseThrow(() -> new ProductReviewNotFoundException("product with id: " + id + " not found"));

        var productReviewDto = productReviewConverter.convert(productReview);

        return productReviewDto;
    }

    @Override
    public ProductReviewDto getProductReviewByProductIdAndUserEmail(Long productId, String email) {
        var productReview = productReviewRepository.findByProductIdAndOwnerEmail(productId, email)
                .orElseThrow(() -> new ProductReviewNotFoundException("user: " + email + " did not review product with id: " + productId));

        var productReviewDto = productReviewConverter.convert(productReview);

        return productReviewDto;
    }

    private boolean isUserOwningProduct(Long reviewerId, Long productId) {
        return boughtProductService.isUserOwnerOfBoughtProduct(reviewerId, productId);
    }

}
