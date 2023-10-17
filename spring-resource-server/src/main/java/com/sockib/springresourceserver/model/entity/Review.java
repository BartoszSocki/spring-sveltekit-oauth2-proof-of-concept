package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Review extends Resource {

    @Column(name = "last_changed_at")
    private LocalDate lastModification;
    private String description;
    private FiveStarScore fiveStarScore;

}
