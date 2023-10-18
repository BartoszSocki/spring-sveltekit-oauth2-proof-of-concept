package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.valueobject.FiveStarScore;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Review extends Resource {

    @Column(name = "last_changed_at")
    private LocalDate lastModification;
    private String description;
    private FiveStarScore fiveStarScore;

}
