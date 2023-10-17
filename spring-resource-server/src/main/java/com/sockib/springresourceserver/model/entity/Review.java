package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.EntityWithHistory;
import jakarta.persistence.Entity;

@Entity
public abstract class Review extends EntityWithHistory {

    private String description;
    private FiveStarScore fiveStarScore;

}
