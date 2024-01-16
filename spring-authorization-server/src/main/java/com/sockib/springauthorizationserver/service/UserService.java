package com.sockib.springauthorizationserver.service;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserService {

    void createUser(OidcUser oidcUser);

}
