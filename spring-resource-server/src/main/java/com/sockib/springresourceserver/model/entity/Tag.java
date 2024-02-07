package com.sockib.springresourceserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Tag {

    @Id
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
