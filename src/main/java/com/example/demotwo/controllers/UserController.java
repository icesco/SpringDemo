package com.example.demotwo.controllers;

import com.example.demotwo.model.User;
import com.example.demotwo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/usr/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("hello")
    public String sayHi() {

        User user = new User();
        log.info("" + user.getId());

        return "Hello world!";
    }

    @GetMapping("list")
    public List<User> users() {
        return userService.allusers();
    }

    @PostMapping("dummy")
    public String addRandomUser(@RequestParam(value = "name", defaultValue = "user") String userName,
                                @RequestParam(value = "surname", defaultValue = "surname") String surname) {

        User user = new User();
        user.setFirstName(userName);
        user.setLastName(surname);

        userService.addUser(user);

        return "Hello world!";
    }
}
