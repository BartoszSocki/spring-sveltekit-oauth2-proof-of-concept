package com.sockib.springresourceserver.entities;

import com.sockib.springresourceserver.entities.embedable.FiveStarScore;
import com.sockib.springresourceserver.entities.mappedsuperclasses.ResourceWithHistory;
import jakarta.persistence.Entity;

@Entity
public abstract class Review extends ResourceWithHistory {

    private String description;
    private FiveStarScore fiveStarScore;

}
