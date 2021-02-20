package com.kustudents.issuetracker.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "viw_users")
public class User{

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "login")
    private UserCredentials userCredentials;

}