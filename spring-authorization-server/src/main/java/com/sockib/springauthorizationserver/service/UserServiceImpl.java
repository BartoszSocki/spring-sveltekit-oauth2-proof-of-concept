package com.sockib.springauthorizationserver.service;

import com.sockib.springauthorizationserver.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    final private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public void createUser(OidcUser oidcUser) {
        String email = oidcUser.getEmail();
    }

}
