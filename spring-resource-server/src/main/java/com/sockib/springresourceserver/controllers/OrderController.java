package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.input.OrderInput;
import com.sockib.springresourceserver.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/products/buy")
@RestController
public class OrderController {

    private OrderService orderService;

    @PostMapping
    ResponseEntity<Void> buyProducts(@Valid @RequestBody OrderInput transaction) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getName();

        orderService.buyProducts(transaction.getProducts(), transaction.getAddress(), email);

        return ResponseEntity.ok().build();
    }

}
