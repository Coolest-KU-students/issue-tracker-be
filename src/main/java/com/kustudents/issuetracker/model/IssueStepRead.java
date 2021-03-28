package com.kustudents.issuetracker.model;

import com.kustudents.issuetracker.model.entity.Issue;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "viw_Issues_Steps")
public class IssueStepRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "step_name")
    private Long stepName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "completed")
    private LocalDateTime completed;

    @Column(name = "response")
    private String response;

}
