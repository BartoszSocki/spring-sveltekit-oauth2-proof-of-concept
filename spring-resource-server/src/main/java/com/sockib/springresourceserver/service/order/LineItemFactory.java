package com.sockib.springresourceserver.service.order;

import com.sockib.springresourceserver.model.entity.LineItem;
import com.sockib.springresourceserver.model.entity.Order;
import com.sockib.springresourceserver.model.entity.Product;

public class LineItemFactory {

    public static LineItem create(Product product) {
        return new LineItem(
                product.getName(),
                product.getPrice().clone(),
                product.getCategory(),
                product.getDescription(),
                product.getImageUrl(),
                product
        );
    }

}
