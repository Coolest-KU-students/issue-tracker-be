package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;
import lombok.Data;

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

    @OneToOne
    @MapsId
    @JoinColumn(name = "login")
    private UserCredentials userCredentials;

}
