package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.input.ProductInput;
import com.sockib.springresourceserver.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class NewProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> addProduct(@Valid @RequestBody ProductInput product) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();

        productService.addNewProduct(product, email);
        return ResponseEntity.ok().build();
    }

}
