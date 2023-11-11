package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.dto.input.PageableInput;
import com.sockib.springresourceserver.model.dto.input.SortInput;
import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.model.respository.BoughtProductRepository;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoughtProductServiceImpl implements BoughtProductService {

    private BoughtProductRepository boughtProductRepository;

    @Override
    public SimplePage<BoughtProduct> searchBoughtProducts(PageableInput pageableInput, SortInput sortInput) {
        return null;
    }
}
