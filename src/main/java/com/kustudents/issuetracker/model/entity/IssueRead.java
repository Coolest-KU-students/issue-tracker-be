package com.kustudents.issuetracker.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "ciw_Issues")
public class IssueRead {

    @Id
    private Long id;

    @Column
    private LocalDateTime closed;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int importance;

    @Column
    private String currentStep;

    @Column
    private String currentResponsible;

    @Column
    private String createdBy;
}
