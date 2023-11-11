package com.sockib.springresourceserver.controllers;

import com.sockib.springresourceserver.model.dto.BoughtProductDto;
import com.sockib.springresourceserver.model.dto.input.PageableInput;
import com.sockib.springresourceserver.model.dto.input.SortInput;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BoughtProductController {

    @QueryMapping
    SimplePage<BoughtProductDto> searchBoughtProducts(@Argument @Valid PageableInput pageable,
                                                      @Argument @Valid SortInput sort) {
        return null;
    }
}
