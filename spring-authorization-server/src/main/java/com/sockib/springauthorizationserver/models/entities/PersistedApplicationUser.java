package com.sockib.springauthorizationserver.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

//@Entity
//@Table(name = "authorization_server.users")
public class PersistedApplicationUser {

//    @Id
    private Long id;
    private String email;
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> authorities;
}
