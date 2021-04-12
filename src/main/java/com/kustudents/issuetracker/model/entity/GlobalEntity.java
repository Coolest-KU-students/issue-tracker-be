package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kustudents.issuetracker.service.AuthenticationService;

import lombok.Data;

@Data
@Embeddable
public class GlobalEntity {

    @JsonIgnore
    @Column(name = "created")
    protected LocalDateTime created;

    @JsonIgnore
    @Column(name = "created_by")
    protected String createdBy;

    @JsonIgnore
    @Column(name = "updated")
    protected LocalDateTime updated;

    @JsonIgnore
    @Column(name = "updated_by")
    protected String updatedBy;

    public void OnUpdateAudit() {
        LocalDateTime currentDate = LocalDateTime.now();
        String Login = AuthenticationService.getLoggedInUserLogin();
        if (createdBy == null || createdBy == "NULL") {
            created = currentDate;
            createdBy = Login;
        }
        updated = currentDate;
        updatedBy = Login;
    }

}
