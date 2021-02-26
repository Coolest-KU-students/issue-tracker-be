package com.kustudents.issuetracker.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationRequest {

    private String login;
    private String password;

}
