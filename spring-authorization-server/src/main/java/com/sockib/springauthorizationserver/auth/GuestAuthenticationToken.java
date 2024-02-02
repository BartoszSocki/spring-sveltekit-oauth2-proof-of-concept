package com.sockib.springauthorizationserver.auth;

import com.sockib.springauthorizationserver.model.entity.UserAccount;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class GuestAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;

    public GuestAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public static GuestAuthenticationToken authenticated(UserAccount userAccount) {
        GuestAuthenticationToken token = new GuestAuthenticationToken(userAccount.getAuthorities());
        token.setPrincipal(userAccount);
        token.setAuthenticated(true);
        return token;
    }

    public static GuestAuthenticationToken unauthenticated(UserAccount userAccount) {
        GuestAuthenticationToken token = new GuestAuthenticationToken(userAccount.getAuthorities());
        token.setPrincipal(userAccount);
        token.setAuthenticated(false);
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    private void setPrincipal(Object principal) {
        this.principal = principal;
    }

}
