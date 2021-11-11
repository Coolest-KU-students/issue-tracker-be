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
@CrossOrigin(origins = "*")
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
        String token = authenticationService.authenticate(request);
        return ResponseEntity.ok(new AuthenticationResponse(token, authenticationService.getLoginFromToken(token)));
    }

    @PostMapping("/pwChange")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserPasswordChange request) {
        String token = authenticationService.changePasswordAndAuthenticate(request.authRequest, request.newPassword);
        return ResponseEntity.ok(new AuthenticationResponse(token, authenticationService.getLoginFromToken(token)));
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestHeader("Authorization") String oldToken) {
        String token = authenticationService.overwriteExistingToken(oldToken);
        return ResponseEntity.ok(new AuthenticationResponse(token, authenticationService.getLoginFromToken(token)));
    }

    @GetMapping("/hello/{name}")
    public String TestingHello(@PathVariable("name") String name) {
        return "Hello User" + name;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInformation userInformation) {
        authenticationService.register(userInformation.login, userInformation.password, userInformation.firstName,
                userInformation.lastName, userInformation.changePasswordOnLogin);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

}
