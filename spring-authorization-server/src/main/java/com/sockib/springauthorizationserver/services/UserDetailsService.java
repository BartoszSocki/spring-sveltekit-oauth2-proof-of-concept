package com.sockib.springauthorizationserver.services;

import com.sockib.springauthorizationserver.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserDetailsService implements UserDetailsManager {

    final private UserDetailsRepository userDetailsRepository;

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {
        throw new RuntimeException("TODO: implement UserDetailsManager.updateUser");
    }

    @Override
    public void deleteUser(String username) {
        throw new RuntimeException("TODO: implement UserDetailsManager.deleteUser");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new RuntimeException("TODO: implement UserDetailsManager.changePassword");
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
