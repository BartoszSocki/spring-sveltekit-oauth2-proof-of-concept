package com.sockib.springauthorizationserver.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private Long id;
    private String name;

    public static GrantedAuthority toGrantedAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getName());
    }

}
