package com.sockib.springauthorizationserver.service;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.sockib.springauthorizationserver.model.embedded.Role;
import com.sockib.springauthorizationserver.model.entity.User;
import com.sockib.springauthorizationserver.model.entity.UserAccount;
import com.sockib.springauthorizationserver.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    final private UserAccountRepository userDetailsRepository;
    final private Name fakerName;

    public UserServiceImpl(UserAccountRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.fakerName = new Faker().name();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    @Override
    public void createUser(OidcUser oidcUser) {
        User user = User.builder()
                .name(retrieveName(oidcUser))
                .surname(retrieveSurname(oidcUser))
                .email(retrieveEmail(oidcUser))
                .build();

        UserAccount userAccount = UserAccount.builder()
                .role(Role.USER)
                .user(user)
                .isAccountEnabled(isEmailVerified(oidcUser))
                .isAccountNonLocked(true)
                .build();

        userDetailsRepository.save(userAccount);
    }

    private String retrieveEmail(OidcUser oidcUser) {
        return oidcUser.getAttribute("email");
    }

    private String retrieveName(OidcUser oidcUser) {
        String name = oidcUser.getAttribute("given_name");

        return name == null ? fakerName.firstName() : name;
    }

    private String retrieveSurname(OidcUser oidcUser) {
        String surname = oidcUser.getAttribute("family_name");

        return surname == null ? fakerName.lastName() : surname;
    }

    private boolean isEmailVerified(OidcUser oidcUser) {
        String isEmailVerified = oidcUser.getAttribute("email_verified");

        return isEmailVerified != null && Boolean.getBoolean(isEmailVerified);
    }

}
