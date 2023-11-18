package com.sockib.springresourceserver.filter;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class NewUserPersistFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            var user = userRepository.findUserByEmail(authentication.getName());
            if (user.isEmpty()) {
                var newUser = new User();
                newUser.setUserMoney(new Money(1000.0, "USD"));
                newUser.setEmail(authentication.getName());

                userRepository.save(newUser);
            }
        }

        filterChain.doFilter(request, response);
    }
}
