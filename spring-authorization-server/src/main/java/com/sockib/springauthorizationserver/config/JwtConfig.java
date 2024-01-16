package com.sockib.springauthorizationserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.io.File;
import java.security.KeyStore;

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

}
