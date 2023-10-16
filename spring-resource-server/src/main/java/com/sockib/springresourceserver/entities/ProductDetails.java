package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.mappedsuperclasses.Resource;
import jakarta.persistence.Entity;

import java.net.URL;
import java.util.List;

@Entity
public class ProductDetails extends Resource {

    // TODO: add required annotations
    private String description;
    private URL imageURL;
    private List<Tag> tags;
    private List<Category> categories;
    private Product product;

}
