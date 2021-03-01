package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.service.AuthenticationService;
import com.kustudents.issuetracker.model.AuthenticationResponse;
import com.kustudents.issuetracker.model.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

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

    //TODO: Find a global service or bean, which would automatically check token from header and then allow access to api ends

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.authenticate(request)));
    }

    //TODO: returns if token is legit and returns a boolean (and if true: user information, ex. login)
//    @PostMapping("/auth")
//    public ResponseEntity<AuthenticationResponse> getAuthenticationStatus(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.authenticate(request)));
//    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInformation userInformation) {
        authenticationService.register(userInformation.login, userInformation.password, userInformation.firstName, userInformation.lastName);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

}
