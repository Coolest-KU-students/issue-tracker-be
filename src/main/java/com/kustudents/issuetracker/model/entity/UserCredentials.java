package com.kustudents.issuetracker.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "viw_users_credentials")
public class UserCredentials {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "is_expired")
    private boolean isExpired;

    @OneToOne(mappedBy = "userCredentials", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

}
