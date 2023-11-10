package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationAndUpdateTimestamp;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends WithCreationAndUpdateTimestamp {

    private String addressLine;
    private String country;
    private String city;
    private String postalCode;

}
