package com.kustudents.issuetracker.model;

public class AuthenticationResponse {

    private final String token;
    private final String login;

    public AuthenticationResponse(String token, String login) {
        this.token = token;
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public String getLogin() {
        return login;
    }

}
