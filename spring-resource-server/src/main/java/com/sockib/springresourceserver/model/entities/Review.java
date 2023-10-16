package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.embedable.FiveStarScore;
import com.sockib.springresourceserver.model.entities.mappedsuperclasses.EntityWithHistory;
import jakarta.persistence.Entity;

@Entity
public abstract class Review extends EntityWithHistory {

    private String description;
    private FiveStarScore fiveStarScore;

}
