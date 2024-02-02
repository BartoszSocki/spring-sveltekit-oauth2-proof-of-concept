package com.sockib.springauthorizationserver.handler;

import com.sockib.springauthorizationserver.service.UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.function.Consumer;

public class OidcUserSuccessAuthenticationConsumer implements Consumer<OidcUser> {

    private final UserService userService;

    public OidcUserSuccessAuthenticationConsumer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void accept(OidcUser oidcUser) {
        userService.saveUserAccount(oidcUser);
    }

}
