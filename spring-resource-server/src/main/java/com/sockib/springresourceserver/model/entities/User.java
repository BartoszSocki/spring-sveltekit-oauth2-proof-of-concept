package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.mappedsuperclasses.EntityWithHistory;
import jakarta.persistence.Entity;

@Entity
public class User extends EntityWithHistory {

    private String email;

}
