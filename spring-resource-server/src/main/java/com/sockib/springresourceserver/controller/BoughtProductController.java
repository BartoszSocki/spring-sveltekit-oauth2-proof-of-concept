package com.sockib.springresourceserver.controller;

import com.sockib.springresourceserver.model.dto.BoughtProductDto;
import com.sockib.springresourceserver.model.dto.input.PageableInput;
import com.sockib.springresourceserver.model.dto.input.SortInput;
import com.sockib.springresourceserver.service.boughtproduct.BoughtProductService;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.sort.Sort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class BoughtProductController {

    private BoughtProductService boughtProductService;
    private ModelMapper modelMapper;

    @QueryMapping
    SimplePage<BoughtProductDto> searchBoughtProducts(@Argument @Valid PageableInput pageable,
                                                      @Argument @Valid SortInput sort,
                                                      @AuthenticationPrincipal Principal principal) {
        var boughtProductsPageable = modelMapper.map(pageable, Pageable.class);
        var boughtProductsSort = modelMapper.map(sort, Sort.class);

        return boughtProductService.searchBoughtProducts(boughtProductsPageable, boughtProductsSort, principal.getName());
    }

}
