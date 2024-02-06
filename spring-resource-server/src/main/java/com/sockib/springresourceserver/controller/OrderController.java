package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.request.OrderRequestDto;
import com.sockib.springresourceserver.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/api/products/buy")
@RestController
public class OrderController {

    private OrderService orderService;

    @PostMapping
    ResponseEntity<Void> placeOrder(@Valid @RequestBody OrderRequestDto orderRequestDto,
                                    @AuthenticationPrincipal Principal principal) {
        orderService.buyProducts(orderRequestDto, principal.getName());

        return ResponseEntity.ok().build();
    }

}
