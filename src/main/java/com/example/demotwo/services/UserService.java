package com.example.demotwo.services;

import com.example.demotwo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> allusers() {
        return (List<User>) repository.findAll();
    }

    public void addUser(final User user) {
        repository.save(user);
    }
}
