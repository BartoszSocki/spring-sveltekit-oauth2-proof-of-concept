package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Tag extends Resource {

    @Column(unique = true)
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
