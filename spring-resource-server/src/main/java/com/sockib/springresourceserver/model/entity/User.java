package com.sockib.springresourceserver.model.entity;

import com.sockib.springresourceserver.model.value.Money;
import com.sockib.springresourceserver.model.entity.mappedsuperclass.WithCreationTimestamp;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
@Entity
public class User extends WithCreationTimestamp {

    @Column(unique = true)
    private String email;

    @AttributeOverride(name = "amount", column = @Column(name = "user_money"))
    private Money userMoney;

}
