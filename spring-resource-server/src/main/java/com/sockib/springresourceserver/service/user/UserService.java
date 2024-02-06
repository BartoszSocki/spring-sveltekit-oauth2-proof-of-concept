package com.sockib.springresourceserver.service.user;

public interface UserService {

    void registerNewUser(String email);
    boolean isUserRegistered(String email);

}
