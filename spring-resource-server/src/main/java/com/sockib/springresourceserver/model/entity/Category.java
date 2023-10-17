package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.Entity;

@Entity
public class Category extends Resource {

    private String name;

}
