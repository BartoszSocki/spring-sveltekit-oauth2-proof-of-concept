package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> addProduct(@Valid @RequestBody ProductInput product,
                                    @AuthenticationPrincipal Principal principal) {
        productService.addNewProduct(product, principal.getName());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productId,
                                       @AuthenticationPrincipal Principal principal) {
        productService.deleteProduct(productId, principal.getName());

        return ResponseEntity.noContent().build();
    }

}
