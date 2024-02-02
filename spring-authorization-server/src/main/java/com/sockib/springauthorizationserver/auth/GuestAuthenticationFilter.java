package com.sockib.springauthorizationserver.auth;

import com.github.javafaker.Faker;
import com.sockib.springauthorizationserver.model.embedded.AccountProvider;
import com.sockib.springauthorizationserver.model.embedded.Role;
import com.sockib.springauthorizationserver.model.entity.User;
import com.sockib.springauthorizationserver.model.entity.UserAccount;
import com.sockib.springauthorizationserver.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;


@Slf4j
public class GuestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final UserService userService;
    private final Faker faker;

    public GuestAuthenticationFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, "GET"));
        this.userService = userService;
        this.faker = new Faker();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserAccount savedUserAccount = userService.saveUserAccount(createGuest());
        GuestAuthenticationToken authentication = GuestAuthenticationToken.unauthenticated(savedUserAccount);
        log.info("created new guest user " + savedUserAccount.getUser());

        return getAuthenticationManager().authenticate(authentication);
    }

    private UserAccount createGuest() {
        String email = faker.internet().safeEmailAddress();
        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        User user = User.builder()
                .email(email)
                .name(name)
                .surname(surname)
                .build();

        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .accountProvider(AccountProvider.INTERNAL)
                .isPasswordSupplied(false)
                .isAccountEnabled(true)
                .isAccountNonLocked(true)
                .role(Role.USER)
                .build();

        return userAccount;
    }
}
