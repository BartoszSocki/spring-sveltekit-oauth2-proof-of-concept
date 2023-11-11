package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.dto.input.PageableInput;
import com.sockib.springresourceserver.model.dto.input.SortInput;
import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.util.search.page.SimplePage;

public interface BoughtProductService {

    SimplePage<BoughtProduct> searchBoughtProducts(PageableInput pageableInput, SortInput sortInput);

}
