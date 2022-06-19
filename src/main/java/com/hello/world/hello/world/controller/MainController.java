package com.hello.world.hello.world.controller;

import com.hello.world.hello.world.model.User;
import com.hello.world.hello.world.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MainController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
