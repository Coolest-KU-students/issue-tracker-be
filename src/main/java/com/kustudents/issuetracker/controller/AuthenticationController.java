package com.kustudents.issuetracker.controller;

import com.kustudents.issuetracker.model.AuthenticationResponse;
import com.kustudents.issuetracker.model.AuthenticationRequest;
import com.kustudents.issuetracker.service.AuthenticationService;
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
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.authenticate(request)));
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new AuthenticationResponse(authenticationService.overwriteExistingToken(token)));
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInformation userInformation) {
        authenticationService.register(userInformation.login, userInformation.password, userInformation.firstName, userInformation.lastName);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

}
