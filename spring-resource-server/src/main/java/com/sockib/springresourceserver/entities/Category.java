package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.mappedsuperclasses.Resource;
import jakarta.persistence.Entity;

@Entity
public class Category extends Resource {

    private String name;

}
