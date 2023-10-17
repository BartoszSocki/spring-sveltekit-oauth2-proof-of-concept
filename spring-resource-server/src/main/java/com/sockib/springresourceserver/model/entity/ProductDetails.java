package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class ProductDetails extends Resource {

    // TODO: add required annotations
    private String description;
//    private String imageURL;
    @ManyToMany
    @JoinTable(
            name = "product_details_tag",
            joinColumns = @JoinColumn(name = "product_details_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
    @ManyToMany
    @JoinTable(
            name = "product_details_category",
            joinColumns = @JoinColumn(name = "product_details_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
//    private Product product;

}
