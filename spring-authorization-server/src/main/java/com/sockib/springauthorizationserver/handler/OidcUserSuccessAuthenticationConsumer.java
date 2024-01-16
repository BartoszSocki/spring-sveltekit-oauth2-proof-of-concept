package com.sockib.springauthorizationserver.handler;

import com.sockib.springauthorizationserver.security.AppUser;
import com.sockib.springauthorizationserver.security.RoleBasedAuthorities;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.function.Consumer;

public class OidcUserSuccessAuthenticationConsumer implements Consumer<OidcUser> {

    private final UserDetailsManager userDetailsManager;

    public OidcUserSuccessAuthenticationConsumer(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public void accept(OidcUser oidcUser) {
        var email = (String) oidcUser.getClaims().get("email");
        var user = new AppUser(RoleBasedAuthorities.USER.authorities(), null, email);

        userDetailsManager.createUser(user);
    }

}
