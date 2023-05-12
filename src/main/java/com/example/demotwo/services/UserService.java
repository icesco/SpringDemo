package com.example.demotwo.services;

import com.example.demotwo.error.UserException;
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

    public UserDTO addUser(final User user) {
        final User addedUser = repository.save(user);
        return mapper.entity2dto(addedUser);
    }

    public UserDTO updateUser(final UserDTO user) {
        Optional<User> requestedUser = repository.findFirstByName(user.name());

        try {
            final User unwrappedUser =requestedUser.orElseThrow();

            final User modified = mapper.dto2entity(user);
            modified.setId(unwrappedUser.getId());


            final User savedUser = repository.save(modified);
            return mapper.entity2dto(savedUser);
        } catch (Exception exc) {
            throw new UserException.UserNotFoundException();
        }
    }
}
