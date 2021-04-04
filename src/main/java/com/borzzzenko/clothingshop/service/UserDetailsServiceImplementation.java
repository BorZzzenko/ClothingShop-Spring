package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.SecurityUser;
import com.borzzzenko.clothingshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImplementation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByUserName(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        return SecurityUser.fromUser(user);
    }
}
