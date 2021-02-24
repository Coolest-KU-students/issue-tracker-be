package com.kustudents.issuetracker.model.entity;

import javax.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity(name = "viw_Issues_Steps")
public class IssueStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "step_id")
    private Long stepId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "completed")
    private LocalDateTime completed;

}
