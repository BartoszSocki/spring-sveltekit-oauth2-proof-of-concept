package com.sockib.springresourceserver.service.user.impl;

import com.sockib.springresourceserver.model.embeddable.Money;
import com.sockib.springresourceserver.model.entity.User;
import com.sockib.springresourceserver.model.respository.UserRepository;
import com.sockib.springresourceserver.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Money DEFAULT_NEW_USER_MONEY = new Money(100.0, "USD");
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerNewUser(String email) {
        userRepository.save(new User(email, DEFAULT_NEW_USER_MONEY.clone()));
    }

    @Override
    public boolean isUserRegistered(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

}
