package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;

//@Entity
public abstract class Review extends Resource {

    private String description;
    private FiveStarScore fiveStarScore;

}
