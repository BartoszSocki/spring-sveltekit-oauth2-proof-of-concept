package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.input.AddressInput;
import com.sockib.springresourceserver.service.transaction.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class TransactionController {

    TransactionService transactionService;

    @MutationMapping
    Boolean buyProducts(@Argument List<Long> productsIds,
                        @Argument @Valid AddressInput address) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        transactionService.buyProducts(productsIds, address, email);
        return true;
    }

}
