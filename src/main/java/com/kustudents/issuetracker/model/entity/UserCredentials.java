package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @Embedded
    private GlobalEntity GE = new GlobalEntity();

    @PrePersist
    @PreUpdate
    public void UpdateAudit() {
        GE.OnUpdateAudit();
    }

}
