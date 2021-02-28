package com.kustudents.issuetracker.model;

public class AuthenticationResponse {

    private final String token;

    //TODO: add user login

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
