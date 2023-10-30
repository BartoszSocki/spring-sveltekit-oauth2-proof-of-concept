package com.sockib.springresourceserver.model.dto;

import com.sockib.springresourceserver.model.embeddable.ProductScore;
import com.sockib.springresourceserver.model.entity.Product;
import com.sockib.springresourceserver.model.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private Long ownerId;
    private String name;
    private MoneyDto price;
    private Integer quantity;
    private String category;
    private List<String> tags;

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
        this.tags = product.getTags().stream().map(Tag::getName).toList();

        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.productScore = product.getProductScore();
    }

}
