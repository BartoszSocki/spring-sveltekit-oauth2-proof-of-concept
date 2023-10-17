package com.sockib.springresourceserver.model.entity.mappedsuperclass;

import jakarta.persistence.Id;
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
public abstract class EntityWithHistory {

    // TODO: add required annotations
    @Id
    private Long id;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;

}
