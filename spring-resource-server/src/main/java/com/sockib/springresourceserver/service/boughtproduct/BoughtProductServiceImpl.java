package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.dto.BoughtProductDto;
import com.sockib.springresourceserver.model.dto.converter.BoughtProductToBoughtProductDtoConverter;
import com.sockib.springresourceserver.model.respository.boughtproducts.BoughtProductRepository;
import com.sockib.springresourceserver.util.search.page.Pageable;
import com.sockib.springresourceserver.util.search.page.SimplePage;
import com.sockib.springresourceserver.util.search.page.SimplePageImpl;
import com.sockib.springresourceserver.util.search.sort.Sort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BoughtProductServiceImpl implements BoughtProductService {

    private final BoughtProductRepository boughtProductRepository;
    private final SortToBoughtProductSorterConverter sortToBoughtProductSorterConverter;
    private final BoughtProductToBoughtProductDtoConverter boughtProductConverter;
    private final ModelMapper modelMapper;
    private final static Integer MAX_BOUGHT_PRODUCT_PAGE_SIZE = 10;

    public BoughtProductServiceImpl(BoughtProductRepository boughtProductRepository, ModelMapper modelMapper) {
        this.boughtProductRepository = boughtProductRepository;
        this.sortToBoughtProductSorterConverter = new SortToBoughtProductSorterConverter();
        this.boughtProductConverter = new BoughtProductToBoughtProductDtoConverter();
        this.modelMapper = modelMapper;
    }

    @Override
    public SimplePage<BoughtProductDto> searchBoughtProducts(Pageable pageable,
                                                             Sort sort,
                                                             String email) {
        if (pageable.getLimit() > MAX_BOUGHT_PRODUCT_PAGE_SIZE) {
            throw new RuntimeException("TODO: add PageSizeTooLargeException");
        }

        var sorter = sortToBoughtProductSorterConverter.convert(sort);
        var boughtProductsPage = boughtProductRepository.searchBoughtProducts(pageable, sorter, email);

        var boughtProducts = boughtProductsPage.getContent()
                .stream()
                .map(boughtProductConverter::convert)
                .toList();

        var page = new SimplePageImpl<>(
                boughtProducts,
                boughtProductsPage.isFirstPage(),
                boughtProductsPage.isLastPage()
        );

        return page;
    }

    @Override
    public Boolean isUserOwnerOfBoughtProduct(Long userId, Long productId) {
        return boughtProductRepository.isUserOwnerOfProduct(userId, productId);
    }

}
