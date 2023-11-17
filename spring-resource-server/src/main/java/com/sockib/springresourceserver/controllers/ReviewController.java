package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/review")
@RestController
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping
    ResponseEntity<ProductReviewDto> addReview(@Valid ReviewInputDto reviewInputDto,
                   @RequestParam(required = true) Long productId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();
        var review = reviewService.addReview(reviewInputDto, productId, email);

        return ResponseEntity.ok(review);
    }

    @GetMapping("/{:id}")
    ResponseEntity<ProductReviewDto> getReview(@PathVariable Long id) {
        var productReviewDto = reviewService.getProductReviewById(id);

        return ResponseEntity.ok(productReviewDto);
    }

}
