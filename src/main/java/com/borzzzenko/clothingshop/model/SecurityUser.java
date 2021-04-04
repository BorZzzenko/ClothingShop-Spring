package com.borzzzenko.clothingshop.model;

import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser  {

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(),
                true, true, true, true,
                user.getRole().getAuthorities()
        );
    }
}
