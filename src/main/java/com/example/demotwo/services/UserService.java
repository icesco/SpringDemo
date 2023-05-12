package com.example.demotwo.services;

import com.example.demotwo.mapper.User2UserDtoMapper;
import com.example.demotwo.model.User;
import com.example.demotwo.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Come mi devo organizzare se voglio due controller ?
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final static User2UserDtoMapper mapper = User2UserDtoMapper.INSTANCE;

    public List<UserDTO> allUsers() {
        List<User> users = (List<User>) repository.findAll();

        if (!users.isEmpty()) {
            return users.stream().map(mapper::entity2dto).collect(Collectors.toList());
        } else {
            return List.of();
            // Collections.EMPTY_LIST;
        }
    }

    public User addUser(final User user) {
        return repository.save(user);
    }

    public void updateUser(final User user) {
        Optional<User> requestedUser = repository.findById(user.getId());

        try {
            User tobeModified = requestedUser.orElseThrow();
            tobeModified.copyValues(user);

            repository.save(tobeModified);
        } catch (Exception exc) {

        }
    }
}
