package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public class User extends ResourceWithHistory {

    private String email;

}
