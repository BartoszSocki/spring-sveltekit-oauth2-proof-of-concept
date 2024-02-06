package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.request.AddProductRequestDto;
import com.sockib.springresourceserver.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

// TODO: add admin access to this controller
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> addProduct(@Valid @RequestBody AddProductRequestDto product,
                                    @AuthenticationPrincipal Principal principal) {
        productService.addNewProduct(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productId,
                                       @AuthenticationPrincipal Principal principal) {
        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

}
