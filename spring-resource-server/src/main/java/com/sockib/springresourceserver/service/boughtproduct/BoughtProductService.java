package com.sockib.springresourceserver.service.boughtproduct;

import com.sockib.springresourceserver.model.dto.response.BoughtProductResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoughtProductService {

    List<BoughtProductResponseDto> queryBoughtProductsForUserWithEmail(String email, Pageable pageable);

    boolean isUserOwnerOfBoughtProduct(long userId, long productId);

}
