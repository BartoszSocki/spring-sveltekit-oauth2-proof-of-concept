package com.sockib.springresourceserver.model.entity.mappedsuperclass;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class WithCreationAndUpdateTimestamp {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name = "updated_at", updatable = false)
    @LastModifiedDate
    private LocalDate updateDate;

}
