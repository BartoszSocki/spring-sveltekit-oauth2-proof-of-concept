package com.sockib.springresourceserver.controllers;

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
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class BoughtProductController {

    private BoughtProductService boughtProductService;
    private ModelMapper modelMapper;

    @QueryMapping
    SimplePage<BoughtProductDto> searchBoughtProducts(@Argument @Valid PageableInput pageableInput,
                                                      @Argument @Valid SortInput sortInput,
                                                      @Argument String email) {
        var pageable = modelMapper.map(pageableInput, Pageable.class);
        var sort = modelMapper.map(sortInput, Sort.class);
        var page = boughtProductService.searchBoughtProducts(pageable, sort, email);

        return page;
    }
}
