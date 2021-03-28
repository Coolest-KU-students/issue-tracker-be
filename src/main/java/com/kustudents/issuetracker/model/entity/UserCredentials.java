package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "viw_users_credentials")
public class UserCredentials {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "is_expired")
    private Boolean isExpired = false;

    @Column(name = "Last_Active")
    private LocalDateTime lastActive;

}
