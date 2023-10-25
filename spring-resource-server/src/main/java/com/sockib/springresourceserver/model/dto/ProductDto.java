package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private Long ownerId;
    private String name;
    private MoneyDto price;
    private Integer quantity;
    private String category;

    private String description;
    private String imageUrl;
    private ProductScore productScore;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.ownerId = product.getOwner().getId();
        this.name = product.getName();
        this.price = new MoneyDto(product.getPrice());
        this.quantity = product.getInventory().getQuantity();
        this.category = product.getCategory().getName();

        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.productScore = product.getProductScore();
    }

}
