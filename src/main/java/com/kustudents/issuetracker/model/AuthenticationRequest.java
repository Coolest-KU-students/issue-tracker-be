package com.kustudents.issuetracker.model;

public class AuthenticationRequest {

    private String login;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthenticationRequest setUsername(String login) {
        this.login = login;
        return this;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
