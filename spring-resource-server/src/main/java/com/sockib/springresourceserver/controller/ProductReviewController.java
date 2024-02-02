package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.service.review.ProductReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductReviewController {

    private ProductReviewService productReviewService;

    @PutMapping("/product/{productId}/review")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> addReview(@Valid @RequestBody ReviewInputDto reviewInputDto,
                                   @PathVariable Long productId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();

        productReviewService.putReview(reviewInputDto, productId, email);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}/review")
    ResponseEntity<ProductReviewDto> getReviewForUser(@PathVariable Long productId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();

        var productReviewDto = productReviewService.getProductReviewByProductIdAndUserEmail(productId, email);

        return ResponseEntity.ok(productReviewDto);
    }

    @GetMapping("/review/{id}")
    ResponseEntity<ProductReviewDto> getReview(@PathVariable Long id) {
        var productReviewDto = productReviewService.getProductReviewById(id);

        return ResponseEntity.ok(productReviewDto);
    }

}
