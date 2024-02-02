package com.sockib.springauthorizationserver.service;

import com.sockib.springauthorizationserver.model.entity.UserAccount;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserService {

    UserAccount createUser(OidcUser oidcUser);
    UserAccount createUser(UserAccount userAccount);

}
