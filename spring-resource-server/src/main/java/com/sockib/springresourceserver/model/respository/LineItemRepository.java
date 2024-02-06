package com.sockib.springresourceserver.model.respository;

import com.sockib.springresourceserver.model.entity.LineItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {

//    @Query("select exists (select 1 from LineItem li where li.owner.id = :userId and li.product.id = :productId)")
//    boolean isUserOwnerOfProduct(Long userId, Long productId);

//    List<LineItem> findBoughtProductsByOwnerEmail(String email, Pageable pageable);

}