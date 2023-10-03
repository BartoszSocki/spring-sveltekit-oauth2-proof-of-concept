package com.sockib.springresourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2ResourceServer(x -> x.jwt(y -> y
                        .authenticationManager()
                        .jwtAuthenticationConverter()
                ))
                .build();
    }

}
