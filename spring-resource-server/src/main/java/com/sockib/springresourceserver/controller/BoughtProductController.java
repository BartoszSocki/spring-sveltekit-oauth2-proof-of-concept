package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.response.BoughtProductResponseDto;
import com.sockib.springresourceserver.service.boughtproduct.BoughtProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor

@RestController
public class BoughtProductController {

    private BoughtProductService boughtProductService;

    @GetMapping("/api/products/bought")
    List<BoughtProductResponseDto> searchBoughtProducts(@AuthenticationPrincipal Principal principal, Pageable pageable) {
        return boughtProductService.queryBoughtProductsForUserWithEmail(principal.getName(), pageable);
    }

}
