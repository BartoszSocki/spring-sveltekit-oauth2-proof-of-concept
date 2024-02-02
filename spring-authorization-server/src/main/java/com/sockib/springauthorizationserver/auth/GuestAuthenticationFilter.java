package com.sockib.springauthorizationserver.auth;

import com.github.javafaker.Faker;
import com.sockib.springauthorizationserver.model.embedded.Role;
import com.sockib.springauthorizationserver.model.entity.User;
import com.sockib.springauthorizationserver.model.entity.UserAccount;
import com.sockib.springauthorizationserver.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.List;


public class GuestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final UserService userService;
    private final Faker faker;

    protected GuestAuthenticationFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(defaultFilterProcessesUrl);
        this.userService = userService;
        this.faker = new Faker();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
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
                .isPasswordSupplied(false)
                .isAccountEnabled(true)
                .isAccountNonLocked(true)
                .role(Role.USER)
                .build();

        UserAccount savedUserAccount = userService.createUser(userAccount);
        Authentication authentication = GuestAuthenticationToken.authenticated(savedUserAccount);
        return authentication;
    }

}
