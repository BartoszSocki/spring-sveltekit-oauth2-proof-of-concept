package com.sockib.springauthorizationserver.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public enum RoleBasedAuthorities {

    USER {
        @Override
        public Collection<? extends GrantedAuthority> authorities() {
            return List.of(
                    new SimpleGrantedAuthority("USER")
            );
        }
    };

    public abstract Collection<? extends GrantedAuthority> authorities();

}
