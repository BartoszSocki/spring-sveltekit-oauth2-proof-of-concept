package com.sockib.springauthorizationserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.io.File;
import java.security.KeyStore;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Configuration
public class JwtConfig {

    @Value("${com.sockib.keystore.absolute.path}")
    private String KEYSTORE_URI;

    @Value("${com.sockib.keystore.passphrase}")
    private String KEYSTORE_PASSPHRASE;

    @Value("${com.sockib.keystore.key.alias}")
    private String KEY_ALIAS;

    @Value("${com.sockib.keystore.key.password}")
    private String KEY_PASSWORD;

    @Value("${com.sockib.client-secret}")
    private String CLIENT_SECRET;

    @Value("${com.sockib.client-id}")
    private String CLIENT_ID;

    @Value("${com.sockib.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${com.sockib.post-logout.redirect-uri}")
    private String POST_LOGOUT_REDIRECT_URI;

    private final PasswordEncoder passwordEncoder;

    public JwtConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    RSAKey rsaKey() throws Exception {
        var ks = KeyStore.getInstance(new File(KEYSTORE_URI), KEYSTORE_PASSPHRASE.toCharArray());
        return RSAKey.load(ks, KEY_ALIAS, KEY_PASSWORD.toCharArray());
    }

    @Bean
    JWKSource<SecurityContext> jwkSource() throws Exception {
        var jwkSet = new JWKSet(rsaKey());
        return (selector, ctx) -> selector.select(jwkSet);
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
        return NimbusJwtDecoder.withPublicKey(rsaKey().toRSAPublicKey()).build();
    }

    TokenSettings tokenSettings() {
        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.of(1, ChronoUnit.DAYS))
                .reuseRefreshTokens(true)
                .build();
    }

    @Bean
    RegisteredClientRepository registeredClientRepository() {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(CLIENT_ID)
                .clientSecret(passwordEncoder.encode(CLIENT_SECRET))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri(REDIRECT_URI)
                .postLogoutRedirectUri(POST_LOGOUT_REDIRECT_URI)
//                .redirectUri("http://localhost:3000/login/oauth2/code/client")
//                .postLogoutRedirectUri("http://localhost:3000")
//                .scope(OidcScopes.EMAIL)
//                .scope("test.read")
                .tokenSettings(tokenSettings())
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .build();

        return new InMemoryRegisteredClientRepository(oidcClient);
    }

}
