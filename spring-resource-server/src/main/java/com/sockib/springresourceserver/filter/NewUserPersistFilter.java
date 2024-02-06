package com.sockib.springresourceserver.filter;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class NewUserPersistFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            var user = userRepository.findUserByEmail(authentication.getName());
            if (user.isPresent()) {
                filterChain.doFilter(request, response);
                return;
            }

            userRepository.save(new User(authentication.getName(), new Money(1000.0, "USD")));
            log.info("registered new user %s", authentication.getName());
        }
        filterChain.doFilter(request, response);
    }

}
