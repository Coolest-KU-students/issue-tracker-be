package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;


import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "viw_Issues_Steps")
public class IssueStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column
    private String responsible;

    @Column
    private Long stepId;

    @Column
    private String comment;

    @Column
    private LocalDateTime completed;

}
