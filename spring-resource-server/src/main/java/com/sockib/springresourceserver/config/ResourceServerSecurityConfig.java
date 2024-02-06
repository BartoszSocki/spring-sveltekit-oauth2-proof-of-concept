package com.sockib.springresourceserver.config;

import com.sockib.springresourceserver.filter.UserRegistrationFilter;
import com.sockib.springresourceserver.service.user.UserService;
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
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
@Slf4j
public class ResourceServerSecurityConfig {

    private final UserService userService;
    @Value("${com.sockib.jwk.uri}")
    private String jwkUri;
    public ResourceServerSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(userRegistrationFilter(), ExceptionTranslationFilter.class)
                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
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
    UserRegistrationFilter userRegistrationFilter() {
        return new UserRegistrationFilter(userService);
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
