package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;


import java.time.LocalDateTime;
import lombok.Data;

@Entity(name = "viw_Issues_Steps")
@Data
public class viwIssuesSteps {

    
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private viwIssues vIssues;


    @Column
    private LocalDateTime completed;

    @Column
    private int stepId;

    @Column
    private String responsible;

    @Column
    private String comment;
}
