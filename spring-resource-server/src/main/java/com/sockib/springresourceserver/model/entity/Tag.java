package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Tag extends WithCreationTimestamp {

    private String name;
    private String value;

}
