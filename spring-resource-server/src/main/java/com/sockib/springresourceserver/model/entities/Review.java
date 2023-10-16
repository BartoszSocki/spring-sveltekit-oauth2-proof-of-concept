package com.sockib.springresourceserver.model.entities;

import com.sockib.springresourceserver.model.entities.embedable.FiveStarScore;
import com.sockib.springresourceserver.model.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public abstract class Review extends ResourceWithHistory {

    private String description;
    private FiveStarScore fiveStarScore;

}
