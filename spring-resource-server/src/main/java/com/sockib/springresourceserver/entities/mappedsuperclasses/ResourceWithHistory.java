package com.sockib.springresourceserver.entities.mappedsuperclasses;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@MappedSuperclass
public abstract class ResourceWithHistory extends Resource {

    // TODO: add required annotations
    private LocalDate creationDate;
    private LocalDate lastModificationDate;

}
