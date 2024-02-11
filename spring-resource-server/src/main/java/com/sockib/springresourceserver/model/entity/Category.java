package com.sockib.springresourceserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
