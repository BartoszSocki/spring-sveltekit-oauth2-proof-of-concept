package com.sockib.springresourceserver.filter;

import com.sockib.springresourceserver.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class UserRegistrationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public UserRegistrationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }

        if (userService.isUserRegistered(authentication.getName())) {
            filterChain.doFilter(request, response);
            return;
        }

        userService.registerNewUser(authentication.getName());
        log.info("registered new user %s", authentication.getName());
        filterChain.doFilter(request, response);
    }

}
