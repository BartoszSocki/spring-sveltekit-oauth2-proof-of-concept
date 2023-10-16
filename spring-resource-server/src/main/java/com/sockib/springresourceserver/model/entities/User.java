package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public class User extends ResourceWithHistory {

    private String email;

}
