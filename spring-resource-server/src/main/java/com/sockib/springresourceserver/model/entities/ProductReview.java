package com.sockib.springresourceserver.model.entities;

import jakarta.persistence.Entity;

@Entity
public class ProductReview extends Review {

    // TODO: add required annotations
    private Product product;

}