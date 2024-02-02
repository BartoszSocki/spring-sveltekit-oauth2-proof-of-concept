package com.sockib.springauthorizationserver.init;

import com.sockib.springauthorizationserver.model.embedded.AccountProvider;
import com.sockib.springauthorizationserver.model.embedded.Role;
import com.sockib.springauthorizationserver.model.entity.User;
import com.sockib.springauthorizationserver.model.entity.UserAccount;
import com.sockib.springauthorizationserver.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserInit(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User user = User.builder()
                .name("John")
                .surname("Snow")
                .email("john.snow@gmail.com")
                .build();

        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .role(Role.USER)
                .accountProvider(AccountProvider.INTERNAL)
                .isAccountEnabled(true)
                .isAccountNonLocked(true)
                .isPasswordSupplied(true)
                .password(passwordEncoder.encode("password"))
                .build();

        userService.saveUserAccount(userAccount);
    }

}
