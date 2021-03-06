package com.kustudents.issuetracker.model.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ciw_Issues")
public class IssueRead {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "closed")
    private LocalDateTime closed;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "importance")
    private int importance;

    @Column(name = "current_step")
    private String currentStep;

    @Column
    private String currentResponsible;

    @Column
    private String createdBy;

}
