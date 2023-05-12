package com.example.demotwo.controllers;

import com.example.demotwo.model.User;
import com.example.demotwo.model.UserDTO;
import com.example.demotwo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/usr/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("list")
    public List<UserDTO> users() {
        return userService.allUsers();
    }

    @PostMapping("dummy")
    public User addRandomUser(@RequestParam(value = "name", defaultValue = "user") String userName,
                              @RequestParam(value = "surname", defaultValue = "surname") String surname,
                              @RequestParam(value = "password", defaultValue = "") String password) {

        User user = new User();
        user.setName(userName);
        user.setSurname(surname);
        user.setPassword(password);

        return userService.addUser(user);
    }
}
