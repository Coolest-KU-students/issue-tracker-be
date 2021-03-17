package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.AuthenticationResponse;
import com.kustudents.issuetracker.model.AuthenticationRequest;
import com.kustudents.issuetracker.service.AuthenticationService;

import java.time.LocalDateTime;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public Boolean changePasswordOnLogin;
    }

    private static class UserPasswordChange {
        public AuthenticationRequest authRequest;
        public String newPassword;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.authenticate(request)));
    }

    @PostMapping("/pwChange")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserPasswordChange request) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.changePasswordAndAuthenticate(request.authRequest, request.newPassword)));
    }


    @GetMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.overwriteExistingToken(token)));
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInformation userInformation) {
        authenticationService.register(userInformation.login, userInformation.password, userInformation.firstName, userInformation.lastName, userInformation.changePasswordOnLogin);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

}
