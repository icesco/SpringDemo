package com.example.demotwo.services;

import com.example.demotwo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findFirstByName(final String name);

}
