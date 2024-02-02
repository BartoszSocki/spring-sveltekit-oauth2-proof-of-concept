package com.sockib.springauthorizationserver.auth;

import com.sockib.springauthorizationserver.model.entity.UserAccount;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class GuestAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;

    public GuestAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public static GuestAuthenticationToken authenticated(UserAccount userAccount) {
        GuestAuthenticationToken token = new GuestAuthenticationToken(userAccount.getAuthorities());
        token.principal = userAccount;
        token.setAuthenticated(true);
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
