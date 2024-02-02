package com.sockib.springauthorizationserver.auth;

import com.sockib.springauthorizationserver.model.entity.UserAccount;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class GuestAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return GuestAuthenticationToken.authenticated((UserAccount) authentication.getPrincipal());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (GuestAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
