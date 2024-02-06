package com.sockib.springresourceserver.service.boughtproduct.impl;

import com.sockib.springresourceserver.model.dto.response.BoughtProductResponseDto;
import com.sockib.springresourceserver.exception.PageSizeTooLargeException;
import com.sockib.springresourceserver.model.respository.LineItemRepository;
import com.sockib.springresourceserver.service.boughtproduct.BoughtProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoughtProductServiceImpl implements BoughtProductService {

    private final LineItemRepository boughtProductRepository;
    private final static Integer MAX_BOUGHT_PRODUCT_PAGE_SIZE = 10;

    public BoughtProductServiceImpl(LineItemRepository boughtProductRepository) {
        this.boughtProductRepository = boughtProductRepository;
    }

    @Override
    public List<BoughtProductResponseDto> queryBoughtProductsForUserWithEmail(String email, Pageable pageable) {
        if (pageable.getPageSize() > MAX_BOUGHT_PRODUCT_PAGE_SIZE) {
            throw new PageSizeTooLargeException("page size: " + pageable.getPageSize() + " too large (>" + MAX_BOUGHT_PRODUCT_PAGE_SIZE + ")");
        }

//        return boughtProductRepository.findBoughtProductsByOwnerEmail(email, pageable)
//                .stream()
//                .map(boughtProductConverter::convert)
//                .toList();
        return List.of();
    }

    @Override
    public boolean isUserOwnerOfBoughtProduct(long userId, long productId) {
        return false;
//        return boughtProductRepository.isUserOwnerOfProduct(userId, productId);
    }

}
