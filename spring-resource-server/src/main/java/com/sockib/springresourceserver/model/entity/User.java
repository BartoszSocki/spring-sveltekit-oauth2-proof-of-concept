package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationTimestamp;
import com.sockib.springresourceserver.model.embeddable.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity(name = "`user`")
public class User extends WithCreationTimestamp {

    @Column(unique = true)
    private String email;

    @AttributeOverride(name = "price", column = @Column(name = "user_money"))
    private Money userMoney;

}
