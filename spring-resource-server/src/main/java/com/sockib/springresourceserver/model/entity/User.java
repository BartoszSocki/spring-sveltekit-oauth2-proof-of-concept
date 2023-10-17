package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.EntityWithHistory;
import jakarta.persistence.Entity;

@Entity
public class User extends EntityWithHistory {

    private String email;

}
