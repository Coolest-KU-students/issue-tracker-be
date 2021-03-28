package com.kustudents.issuetracker.controller;

import lombok.RequiredArgsConstructor;

import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.UserRead;
import com.kustudents.issuetracker.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public Iterable<UserRead> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public UserRead getUser(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }

    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{login}")
    public void deleteStep(@PathVariable("login") String login) {
        userService.changeUserExpiration(login);
    }

}
