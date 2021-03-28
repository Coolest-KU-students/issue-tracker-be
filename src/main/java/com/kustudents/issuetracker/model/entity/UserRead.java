package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ciw_users")
public class UserRead {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_expired")
    private Boolean isExpired;
}
