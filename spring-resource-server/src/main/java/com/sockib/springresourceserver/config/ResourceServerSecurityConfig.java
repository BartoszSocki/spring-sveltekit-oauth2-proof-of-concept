package com.sockib.springresourceserver.config;

import com.sockib.springresourceserver.filter.NewUserPersistFilter;
import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug = true)
@AllArgsConstructor
@Slf4j
public class ResourceServerSecurityConfig {

    private UserRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
                .addFilterAfter(newUserPersistFilter(), AuthorizationFilter.class)
                .authorizeHttpRequests(x -> x.anyRequest().permitAll())
                .oauth2ResourceServer(x -> x.jwt(y -> y
//                        .authenticationManager()

                        .jwkSetUri("http://localhost:8080/oauth2/jwks")
                ))
                .csrf(x -> x.disable())
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    NewUserPersistFilter newUserPersistFilter() {
        return new NewUserPersistFilter(userRepository);
    }

    @Bean
    AuthenticationSuccessHandler createUserIfNotExists() {
        return ((request, response, authentication) -> {
            var user = userRepository.findUserByEmail(authentication.getName());
            if (user.isEmpty()) {
                var newUser = new User(authentication.getName(), new Money(1000.0, "USD"));
                userRepository.save(newUser);
                log.info("Added new user: " + newUser.getEmail());
            }
        });
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
