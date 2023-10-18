package com.sockib.springresourceserver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@PrimaryKeyJoinColumn(name = "review_id")
public class UserReview extends Review {

    @ManyToOne
    private User user;

}
