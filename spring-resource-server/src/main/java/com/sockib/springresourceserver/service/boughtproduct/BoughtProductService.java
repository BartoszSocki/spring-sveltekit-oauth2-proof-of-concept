package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.dto.BoughtProductDto;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.sort.Sort;

public interface BoughtProductService {

    SimplePage<BoughtProductDto> searchBoughtProducts(Pageable pageable, Sort sort, String email);

}
