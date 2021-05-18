package com.kustudents.issuetracker.controller;

import lombok.RequiredArgsConstructor;

import com.kustudents.issuetracker.model.entity.User;
import com.kustudents.issuetracker.model.UserRead;
import com.kustudents.issuetracker.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private static class UserPagingConfiguration {
        public Boolean showExpired;
        public Integer page;
        public Integer size;
        public String orderBy;
        public Boolean ascending;
    }

    @GetMapping("/")
    public Iterable<UserRead> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{login}")
    public UserRead getUser(@PathVariable("login") String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/")
    public Page<UserRead> getAllUsersPaginated(@RequestBody UserPagingConfiguration config) {
        return userService.getUsersPaginatedAndFiltered(config.showExpired, config.page, config.size, config.orderBy,
                config.ascending);
    }

    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable("login") String login) {
        userService.changeUserExpiration(login);
    }

}
