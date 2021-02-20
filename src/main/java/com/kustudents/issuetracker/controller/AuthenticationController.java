package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private static class UserInformation {
        public String login;
        public String password;
        public String firstName;
        public String lastName;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInformation userInformation) {
        authenticationService.register(userInformation.login, userInformation.password, userInformation.firstName, userInformation.lastName);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

}