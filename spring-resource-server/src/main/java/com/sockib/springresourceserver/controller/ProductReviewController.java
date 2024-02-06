package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.ProductReviewDto;
import com.sockib.springresourceserver.model.dto.input.ReviewInputDto;
import com.sockib.springresourceserver.service.review.ProductReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductReviewController {

    private ProductReviewService productReviewService;

    @PutMapping("/product/{productId}/review")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> addReview(@Valid @RequestBody ReviewInputDto reviewInputDto,
                                   @PathVariable Long productId,
                                   @AuthenticationPrincipal Principal principal) {
        productReviewService.putReview(reviewInputDto, productId, principal.getName());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}/review")
    ResponseEntity<ProductReviewDto> getReviewForUser(@PathVariable Long productId,
                                                      @AuthenticationPrincipal Principal principal) {
        var productReviewDto = productReviewService.getProductReviewByProductIdAndUserEmail(productId, principal.getName());

        return ResponseEntity.ok(productReviewDto);
    }

    @GetMapping("/review/{id}")
    ResponseEntity<ProductReviewDto> getReview(@PathVariable Long id) {
        var productReviewDto = productReviewService.getProductReviewById(id);

        return ResponseEntity.ok(productReviewDto);
    }

}
