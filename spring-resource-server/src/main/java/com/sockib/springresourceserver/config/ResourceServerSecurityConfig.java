package com.sockib.springresourceserver.config;

import com.sockib.springresourceserver.filter.NewUserPersistFilter;
import com.sockib.springresourceserver.model.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
@Slf4j
public class ResourceServerSecurityConfig {

    private UserRepository userRepository;
    @Value("${com.sockib.jwk.uri}")
    private String jwkUri;

    public ResourceServerSecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterAfter(newUserPersistFilter(), AuthorizationFilter.class)
                .authorizeHttpRequests(x -> x.anyRequest().permitAll())
                .oauth2ResourceServer(x -> x
                        .jwt(y -> y
                                .jwkSetUri(jwkUri)
                        )
                )
                .csrf(x -> x.disable())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    NewUserPersistFilter newUserPersistFilter() {
        return new NewUserPersistFilter(userRepository);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

}
