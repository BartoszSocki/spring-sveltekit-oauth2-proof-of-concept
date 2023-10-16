package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.mappedsuperclasses.Resource;
import jakarta.persistence.Entity;

@Entity
public class Tag extends Resource {

    private String name;

}
