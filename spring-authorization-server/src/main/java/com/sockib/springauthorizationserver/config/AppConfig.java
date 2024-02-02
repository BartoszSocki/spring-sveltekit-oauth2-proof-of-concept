package com.sockib.springauthorizationserver.config;

import com.sockib.springauthorizationserver.auth.GuestAuthenticationFilter;
import com.sockib.springauthorizationserver.auth.GuestAuthenticationProvider;
import com.sockib.springauthorizationserver.handler.FederatedIdentityAuthenticationSuccessHandler;
import com.sockib.springauthorizationserver.handler.OidcUserSuccessAuthenticationConsumer;
import com.sockib.springauthorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
public class AppConfig {

    final UserService userService;
    final UserDetailsService userDetailsService;

    @Bean
    @Order(1)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/signin"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(x -> x
                        .requestMatchers("/oauth2/callback/**").permitAll()
                        .requestMatchers("/login/guest", "/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(x -> x
                        .loginPage("/signin").permitAll()
                )
                .addFilterAfter(guestAuthenticationFilter(), LogoutFilter.class)
                .oauth2Login(x -> x
                        .authorizationEndpoint(w -> w.baseUri("/oauth2/login"))
                        .redirectionEndpoint(w -> w.baseUri("/oauth2/callback/**"))
                        .successHandler(oidcUserAuthenticationSuccessHandler()))
                .build();
    }

    AuthenticationSuccessHandler oidcUserAuthenticationSuccessHandler() {
        var oidcSuccessAuthenticationConsumer = new OidcUserSuccessAuthenticationConsumer(userService);

        var authenticationSuccessHandler = new FederatedIdentityAuthenticationSuccessHandler();
        authenticationSuccessHandler.setOidcUserConsumer(oidcSuccessAuthenticationConsumer);

        return authenticationSuccessHandler;
    }

    GuestAuthenticationFilter guestAuthenticationFilter() {
        var sessionSecurityContextRepository = new HttpSessionSecurityContextRepository();

        var filter = new GuestAuthenticationFilter("/login/guest", userService);
        filter.setAuthenticationManager(authenticationManager());
        filter.setSecurityContextRepository(sessionSecurityContextRepository);

        return filter;
    }

    AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(guestAuthenticationProvider()));
    }

    AuthenticationProvider guestAuthenticationProvider() {
        return new GuestAuthenticationProvider();
    }

}
