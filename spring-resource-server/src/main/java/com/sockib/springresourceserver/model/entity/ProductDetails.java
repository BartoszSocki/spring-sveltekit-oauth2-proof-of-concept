package com.sockib.springresourceserver.model.entity;

import jakarta.persistence.Entity;

import java.net.URL;
import java.util.List;

@Entity
public class ProductDetails {

    // TODO: add required annotations
    private String description;
    private URL imageURL;
    private List<Tag> tags;
    private List<Category> categories;
    private Product product;

}
