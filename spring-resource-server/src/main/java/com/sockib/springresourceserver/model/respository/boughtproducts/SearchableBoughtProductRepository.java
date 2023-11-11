package com.sockib.springresourceserver.model.respository.boughtproducts;

import com.sockib.springresourceserver.model.entity.BoughtProduct;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.sort.Sorter;

public interface SearchableBoughtProductRepository {

    SimplePage<BoughtProduct> searchBoughtProducts(Pageable pageable, Sorter<BoughtProduct> sorter, String email);

}
