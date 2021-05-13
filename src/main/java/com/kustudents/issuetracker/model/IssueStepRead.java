package com.kustudents.issuetracker.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "ciw_issues_steps")
public class IssueStepRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "issue_id")
    private Long issue_id;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "completed")
    private LocalDateTime completed;

    @Column(name = "updated_by")
    protected String updatedBy;

}
