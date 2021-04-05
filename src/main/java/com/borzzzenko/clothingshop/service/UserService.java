package com.borzzzenko.clothingshop.service;

import com.borzzzenko.clothingshop.model.User;
import com.borzzzenko.clothingshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public Optional<User> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void deleteByUserName(String userName) {
        User user = findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        repository.delete(user);
    }
}
